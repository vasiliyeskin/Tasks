package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by Vaisiliy Es'kin on 02/07/17.
 */
public class Consumer implements Runnable
{
    @Override
    public void run()
    {
        if(Thread.currentThread().isInterrupted())
            return;

        try
        {
            Thread.sleep(500);
            while (!Thread.currentThread().isInterrupted()) {
                ShareItem item = queue.take();
                System.out.println("Processing " + item.toString());
            }
        }
        catch (InterruptedException e)
        {
            return;
        }
    }

    private TransferQueue<ShareItem> queue;
    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }
}

