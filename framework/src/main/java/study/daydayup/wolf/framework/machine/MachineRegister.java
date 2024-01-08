package study.daydayup.wolf.framework.machine;

import study.daydayup.wolf.common.util.net.IPAddressUtil;


/**
 * @author yik
 */
public interface MachineRegister {
    default int getLocalMachineId() {
        if (0 != LocalMachineCache.id) {
            return LocalMachineCache.id;
        }

        String ip = IPAddressUtil.getLocalIPV4Address();
        Machine machine = getMachine(ip);
        if (machine == null) {
            machine = registerMachine(ip);
        }

        LocalMachineCache.id = machine.getId();
        return machine.getId();

    }

    default Machine registerLocalMachine() {
        String ip = IPAddressUtil.getLocalIPV4Address();
        Machine machine = registerMachine(ip);
        return machine;
    }

    Machine getMachine(String ip);

    Machine registerMachine(String ip);

    class LocalMachineCache {
        public volatile static int id;
    }
}
