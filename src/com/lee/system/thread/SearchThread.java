package com.lee.system.thread;

import com.lee.system.exception.MyHttpException;
import com.lee.system.http.HttpRequestContnet;
import com.lee.system.http.HttpRequestHandler;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by libia on 2016/1/30.
 */
public class SearchThread  implements Runnable {


    private LinkedBlockingQueue<HttpRequestContnet> queue;
    private CountDownLatch beginSign ;
    private CountDownLatch endSign;

    public SearchThread (LinkedBlockingQueue list , CountDownLatch beginSign , CountDownLatch  endSign)
    {
        this.queue = list;
        this.beginSign = beginSign;
        this.endSign = endSign;
    }



    public void put( List l )
    {
        this.queue.addAll(l);
    }

    public void put( HttpRequestContnet  h )
    {
        this.queue.add(h);
    }






    @Override
    public void run() {
        try {


            int i = 0 ;
//            Iterator<HttpRequestContnet> iterator =   this.queue.iterator();

            while ( true )
            {


                /**
                 * 当队列不为空 ， 有任务的时候就开始扒内容
                 */
                if( null != this.queue && !this.queue.isEmpty() )
                {

                    // this.queue.take() 拿取其中的一个任务
                    beginSign.await();
                    HttpRequestContnet h =  this.queue.take();
                    String content = new HttpRequestHandler().request(h);
                    System.out.println("线程 ："+Thread.currentThread().getName()+"请求" + h.getUrl() + "的返回的内容为:");
                    System.out.println("==================================================");
                    System.out.println(content);
                    System.out.println("==================================================");
                    endSign.countDown();
                    System.out.println("请求" + h.getUrl() + "结束:");
                }


            }

//            while( iterator.hasNext() )
//            {
//
//                HttpRequestContnet  h  = iterator.next();
//                String content = new HttpRequestHandler().request(h);
//                System.out.println("请求" + h.getUrl() + "的返回的内容为:");
//                System.out.println("==================================================");
//                System.out.println(content);
//                System.out.println("==================================================");
//                System.out.println("请求" + h.getUrl() + "结束:");
//            }

        } catch (MyHttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
