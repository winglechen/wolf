package study.daydayup.wolf.demo.my.starter.thread.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * study.daydayup.wolf.demo.my.starter.thread.semaphore
 *
 * @author Wingle
 * @since 2020/4/2 2:10 下午
 **/
public class Main {



    class Crawler extends Thread {

    }

    class ParserGateway {
        private final Semaphore semaphore;
        private final int permits;
//        private final static Random random = new Random(314159);

        public ParserGateway(int permits) {
            this.permits = permits;
            this.semaphore = new Semaphore(permits);
        }

        public void parse() {

        }
    }

    class Parser extends Thread {

    }
}
