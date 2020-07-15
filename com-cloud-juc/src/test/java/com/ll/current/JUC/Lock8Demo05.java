package com.ll.current.JUC;

class Phone{

    public static synchronized  void sendEmail() throws Exception{
      // TimeUnit.SECONDS.sleep(4);
        System.out.println("*****sendEmail==");
    }
    public static synchronized  void sendMess() throws Exception{
        System.out.println("*********sendMess===");
    }
    public   void sendHello() throws Exception{
        System.out.println("*********sendHello===");
    }
}

/**
 * @ Author     ：liul
 * @ Date       ：Created in 16:27 2020/7/5
 * @ Description：${
 *   1.标准访问，请问先打印邮件再打印是短信---邮件
 *   2.现在两个加synichronized，在邮件方法里边暂停4s结果先打印？？---是邮件
 *   3.新增不加synchronized 锁的普通的方法先打印的是？？？---hello
 *   4.两部手机一个调用邮件一个调用短信？？---先打印短信
 *   5.两个静态 同步 同一部手机方法 先打印那个？？-- 加延迟了短信，不加是邮件
 *   6.俩个静态 同步 两部手机 先打印那个？？？--？加延迟了短信，不加是邮件
 *   7.一个静态 同步 同一部手机方法 先打印那个？？--短信
 *   8.一个静态同步，一个普通，两部手机 先打印那个？？--短信
 *
 *   假设一个类对象里边如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中一个synchronized方法了，其他线程都只能等待，
 *   换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized 方法
 *   synchronnized 锁的是当前对象this,被锁定后，其他线程都不能进入到当前对象的其他的snchronized 方法
 *
 *   加个普通方法后和同步锁无关。
 *   换成两个对象不是同一把锁 ，情况立刻变化
 *
 *   synchronized 实现同步的基础，java 中的每一个对象可以作为锁（小锁锁对象）
 *    具体表现一下三种形式：
 *    对于普通方法，锁是当前实例对象
 *    同步方法块，锁是synchronized括号里配置的对象。
 *
 *    对于静态同步方法，锁是当前类的class 对象（大锁锁类的class对象全局）。
 *
 *    当一个线程试图访问同步代码块时，它首先必须得到锁，退出或者抛出异常时必须释放锁。
 *    也就是说如果一个实例对象的非静态同步方法获取锁后看，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁喉才能获取锁。
 *    可是别的实例对象的非静态同步方法因为跟该是咧对象的非静态同步方法用的是不同的锁。
 *    所以不需等待该是该是实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *
 *    所有的静态同步方法用的也是同一把锁----》类对象本身
 *    这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞争条件的。
 *    但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁喉才能获取锁。
 *    而不管是同一个实例对象的静态同步方法之间。
 *    还是不同的实例对象的静态同步方法之间，只要他们同一个类的实例对象
 * }
 * @ Modified By：
 * @Version: $1.00$
 */
public class Lock8Demo05 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2=new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                phone2.sendMess();
              //  phone2.sendMess();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                phone2.sendHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}
