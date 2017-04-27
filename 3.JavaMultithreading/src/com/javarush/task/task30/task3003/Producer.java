package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by Vaisiliy Es'kin on 02/07/17.
 */
public class Producer implements Runnable
{
    @Override
    public void run()
    {
        if(Thread.currentThread().isInterrupted())
            return;

        for (int i = 1; i <= 9; i++)
        {
            System.out.format("Элемент 'ShareItem-%d' добавлен%n",i);
            queue.offer(new ShareItem(String.format("ShareItem-%d",i), i));

            try
            {
                Thread.currentThread().sleep(100);
            }
            catch (InterruptedException e)
            {
            }

            if (queue.hasWaitingConsumer()) {
                System.out.println("Consumer в ожидании!");
            }
        }
    }

    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }
}
