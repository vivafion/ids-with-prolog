// 1.Import the jpcap library \\

import jpcap.*;
import jpcap.packet.Packet;
import jpcap.PacketReceiver;


// 2.Create a class called JSniffer that’s implements JpcapHandler

// (This interface is used to define a method to analyze the captured packets,



class Sniffer implements PacketReceiver

{

// 3.The handlePacket() method is called everytime a packet is captured

// and the parameter is the packet to be analyzed \\


public void receivePacket(Packet packet) {
System.out.println(packet);

}


// 4.The main comes now! \\


public static void main(String[] args) throws java.io.IOException

{

// 5.The getDeviceDescription() is a static method of class Jpcap

// and can be called using the class name itself!

// It returns the description of the interfaces which is saved in lists[] \\

NetworkInterface[] lists=jpcap.JpcapCaptor.getDeviceList();

//Jpcap.getDeviceDescription();

System.out.println("\n\t\t***My Simple Network Sniffer***\n");

System.out.println("Found following devices:");
for(NetworkInterface s: lists)
{
System.out.println("Name: " + s.name +" Description: " + s.description);

}



// 6.The openDevice() is a static method of Jpcap class

// and returns an instance of this class.

// The parameters are in the following order:

// (i)device (ii)snaplen (iii)promisc (iv)to_ms \\

JpcapCaptor jpcap=JpcapCaptor.openDevice(JpcapCaptor.getDeviceList()[1],1000,false,20);

// 7.We use the instance returned by the openDevice() methos to capture packets

// using loopPacket() that captures the specified number of packets consecutively

// The parameter list is: (i)count (ii)a Jpcap handler \\

jpcap.loopPacket(-1,new Sniffer());

}


}
