# Simulator
set ns [new Simulator]

# Colors
$ns color 1 Blue
$ns color 2 Red

# Trace files
set tr [open 11a.tr w]
$ns trace-all $tr
set nf [open 11a.nam w]
$ns namtrace-all $nf

# Congestion window files
set w0 [open WinFile0 w]
set w1 [open WinFile1 w]

# Finish procedure
proc Finish {} {
    global ns tr nf
    $ns flush-trace
    close $tr
    close $nf
    exec nam 11a.nam &
    exec xgraph WinFile0 WinFile1 &
    exit 0
}

# Plot window
proc Plot {tcp file} {
    global ns
    set t [$ns now]
    puts $file "$t [$tcp set cwnd_]"
    $ns at [expr $t+0.1] "Plot $tcp $file"
}

# Nodes
for {set i 0} {$i < 6} {incr i} {
    set n($i) [$ns node]
}

# Links
$ns duplex-link $n(0) $n(2) 2Mb 10ms DropTail
$ns duplex-link $n(1) $n(2) 2Mb 10ms DropTail
$ns duplex-link $n(2) $n(3) 0.6Mb 100ms DropTail

# LAN
set lan [$ns newLan "$n(3) $n(4) $n(5)" \
         0.5Mb 40ms LL Queue/DropTail MAC/802_3 Channel]

# Orientation
$ns duplex-link-op $n(0) $n(2) orient right-down
$ns duplex-link-op $n(1) $n(2) orient right-up
$ns duplex-link-op $n(2) $n(3) orient right

# Queue settings
$ns queue-limit $n(2) $n(3) 20
$ns duplex-link-op $n(2) $n(3) queuePos 0.5

# Loss model
set loss [new ErrorModel]
$loss ranvar [new RandomVariable/Uniform]
$loss drop-target [new Agent/Null]
$ns lossmodel $loss $n(2) $n(3)

# TCP 0
set tcp0 [new Agent/TCP/Newreno]
$tcp0 set fid_ 1
$ns attach-agent $n(0) $tcp0

set sink0 [new Agent/TCPSink/DelAck]
$ns attach-agent $n(4) $sink0
$ns connect $tcp0 $sink0

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

# TCP 1
set tcp1 [new Agent/TCP/Newreno]
$tcp1 set fid_ 2
$ns attach-agent $n(5) $tcp1

set sink1 [new Agent/TCPSink/DelAck]
$ns attach-agent $n(1) $sink1
$ns connect $tcp1 $sink1

set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1

# Schedule
$ns at 0.1 "$ftp0 start"
$ns at 0.1 "Plot $tcp0 $w0"
$ns at 0.5 "$ftp1 start"
$ns at 0.5 "Plot $tcp1 $w1"
$ns at 25.0 "$ftp0 stop"
$ns at 25.1 "$ftp1 stop"
$ns at 25.2 "Finish"

# Run
$ns run
