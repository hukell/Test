package com.mb.test.utils.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2016/10/9 0009.
 *
 * 1、Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，需要将 Subject转换为一个 SerializedSubject，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
 2、PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
 3、ofType操作符只发射指定类型的数据，其内部就是filter+cast
 */

public class RxBus {

    private static volatile RxBus mInstance;

    private final Subject bus;


    public RxBus()
    {
        bus = new SerializedSubject<Object,Object>(PublishSubject.create());
    }

    /**
     * 单例模式RxBus
     *
     * @return
     */
    public static RxBus getInstance()
    {

        RxBus rxBus2 = mInstance;
        if (mInstance == null)
        {
            synchronized (RxBus.class)
            {
                rxBus2 = mInstance;
                if (mInstance == null)
                {
                    rxBus2 = new RxBus();
                    mInstance = rxBus2;
                }
            }
        }

        return rxBus2;
    }


    /**
     * 发送消息
     *
     * @param o
     */
    public void post(Object o)
    {
        bus.onNext(o);
    }
    /**
     * 接收消息
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObserverable(Class<T> eventType)
    {
        return bus.ofType(eventType);
    }
}

/**
四、发送事件
        RxBus.getInstance().post(new StudentEvent("007","小明"));

        五、接收事件
        rxSbscription=RxBus.getInstance().toObserverable(StudentEvent.class)
        .subscribe(new Action1<StudentEvent>() {
@Override
public void call(StudentEvent studentEvent) {
        textView.setText("id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName());
        }
        });
        注：rxSbscription是Sbscription的对象，我们这里把RxBus.getInstance().toObserverable(StudentEvent.class)赋值给rxSbscription以方便生命周期结束时取消订阅事件

        六、取消订阅
@Override
protected void onDestroy() {
        if (!rxSbscription.isUnsubscribed()){
        rxSbscription.unsubscribe();
        }
        super.onDestroy();
 **/