package study.daydayup.wolf.common.util.net;


import org.apache.commons.validator.routines.InetAddressValidator;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * IPUtil
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2022/8/26 16:52
 **/
public class IPAddressUtil {

    private static final InetAddressValidator VALIDATOR = InetAddressValidator.getInstance();

    public static boolean isIPV4Address(String ip) {
        return VALIDATOR.isValidInet4Address(ip);
    }

    /**
     * 获取本机当前生效的IPV4地址
     *
     * @return
     */
    public static String getLocalIPV4Address() {
        try {
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                NetworkInterface item = e.nextElement();
                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                    if (item.isLoopback() || !item.isUp()) {
                        continue;
                    }
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address) address.getAddress();
                        return inet4Address.getHostAddress();
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        System.out.println(isIPV4Address("12.12.12.1"));
        System.out.println(isIPV4Address("12.12.12"));
        System.out.println(isIPV4Address("abs"));
    }
}
