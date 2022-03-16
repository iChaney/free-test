package com.qiang.design.observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * 观察者模式 西游记里面悟空请求菩萨降服红孩儿，菩萨洒了一地水招来一个老乌龟，这个乌龟就是观察者，他观察菩萨洒水这个动作。
 *
 * @author liq
 * @date 2021/6/10 10:29
 */
public class ObserverTest {
    public static void main(String[] args) {
        Buddha buddha = new Buddha();
        buddha.add(new Tortoise());
        buddha.add(new LandGod());
        buddha.sprinkle();
    }
}

class Buddha {
    public String name = "观世音菩萨";
    List<Observer> observers = new ArrayList<>();

    public void add(Observer observer) {
        observers.add(observer);
    }

    // 洒水
    public void sprinkle() {
        System.out.println("菩萨开始洒水...");
        for (Observer observer : observers) {
            Event event = new SprinkleEvent(this);
            event.setActionTime(LocalDateTime.now());
            observer.action(event);
        }
    }

    // 洒水
    public void bye() {
        System.out.println("菩萨走了...");
        for (Observer observer : observers) {
            Event event = new ByeEvent(this);
            event.setActionTime(LocalDateTime.now());
            observer.action(event);
        }
    }
}

class Event extends EventObject {
    private LocalDateTime actionTime;

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public Event(Object source) {
        super(source);
    }
}

class SprinkleEvent extends Event {
    public SprinkleEvent(Object source) {
        super(source);
    }
}

class ByeEvent extends Event {
    public ByeEvent(Object source) {
        super(source);
    }
}

interface Observer {
    void action(Event event);
}

class Tortoise implements Observer {
    public void action(Event event) {
        if (event instanceof SprinkleEvent) {
            Buddha source = (Buddha)event.getSource();
            System.out.println("听说" + source.name + "在" + event.getActionTime() + "洒了水");
            System.out.println("老乌龟来了...");
        } else if (event instanceof ByeEvent) {
            Buddha source = (Buddha)event.getSource();
            System.out.println("听说" + source.name + "在" + event.getActionTime() + "走了");
            System.out.println("老乌龟也得走了...");
        }
    }
}

class LandGod implements Observer {
    public void action(Event event) {
        if (event instanceof SprinkleEvent) {
            Buddha source = (Buddha)event.getSource();
            System.out.println("听说" + source.name + "在" + event.getActionTime() + "洒了水");
            System.out.println("土地神来了...");
        } else if (event instanceof ByeEvent) {
            Buddha source = (Buddha)event.getSource();
            System.out.println("听说" + source.name + "在" + event.getActionTime() + "走了");
            System.out.println("土地神也得走了...");
        }
    }
}
