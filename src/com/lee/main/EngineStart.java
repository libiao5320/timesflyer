package com.lee.main;

import com.lee.system.http.HttpRequestContnet;
import com.lee.system.http.HttpRequestHandler;
import com.lee.system.thread.SearchThread;

import java.util.List;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by libia on 2016/2/1.
 */
public class EngineStart  {

        public static  void main (String [] args)
        {

            CountDownLatch begSignal = new CountDownLatch(1);
            CountDownLatch endSignal = new CountDownLatch(5);



            try {
                /**
                 * 种子队列
                 */
                LinkedBlockingQueue<HttpRequestContnet> list = new LinkedBlockingQueue<>();


                /**
                 *
                 * 线程池
                 * */
                ExecutorService executorService = Executors.newCachedThreadPool();
                HttpRequestContnet requestContnet1 = new HttpRequestContnet("http://mp.weixin.qq.com/s?__biz=MjM5ODAxODQ0MA==&mid=2651094972&idx=1&sn=3c78b13d184aaa39f16e2911f8e60f0e&scene=23&srcid=0411s7Q6tI1Y3HG9NqY5hqCa#rd");
                HttpRequestContnet requestContnet2 = new HttpRequestContnet("http://hn.qq.com/");
                HttpRequestContnet requestContnet3 = new HttpRequestContnet("http://dota.178.com/");
                HttpRequestContnet requestContnet4 = new HttpRequestContnet("http://www.tianya.cn/");
                HttpRequestContnet requestContnet5 = new HttpRequestContnet("http://www.csdn.net/");
                HttpRequestContnet requestContnet6 = new HttpRequestContnet("http://www.cnblogs.com/");
                HttpRequestContnet requestContnet7 = new HttpRequestContnet("http://blog.sina.com.cn/");
                HttpRequestContnet requestContnet8 = new HttpRequestContnet("http://www.youku.com/");
                HttpRequestContnet requestContnet9 = new HttpRequestContnet("http://www.sina.com.cn/");
                HttpRequestContnet requestContnet10 = new HttpRequestContnet("http://www.rednet.cn/");


                list.add(requestContnet1);
                list.add(requestContnet2);
                list.add(requestContnet3);
                list.add(requestContnet4);
                list.add(requestContnet5);
                list.add(requestContnet6);
                list.add(requestContnet7);
                list.add(requestContnet8);
                list.add(requestContnet9);
                list.add(requestContnet10);
//
                SearchThread searchThread1 = new SearchThread(list ,begSignal,endSignal);
                SearchThread searchThread2 = new SearchThread(list ,begSignal,endSignal);
                SearchThread searchThread3 = new SearchThread(list ,begSignal,endSignal);
                SearchThread searchThread4 = new SearchThread(list ,begSignal,endSignal);
                SearchThread searchThread5 = new SearchThread(list  ,begSignal,endSignal);
                executorService.execute(searchThread1);
                executorService.execute(searchThread2);
                executorService.execute(searchThread3);
                executorService.execute(searchThread4);
                executorService.execute(searchThread5);

                begSignal.countDown();  //统一起跑
                endSignal.await();      //等待运动员到达终点
                System.out.println("所有线程已经扫描完成");
                executorService.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

}
