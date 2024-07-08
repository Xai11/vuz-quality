package org.example.demo1.criteria;

import org.example.demo1.Person;

import java.util.ArrayList;
import java.util.List;

public class SingleDataBase {
    private static SingleDataBase instance = null;
    private int vuz_id;
    private int spec_id;
    private ArrayList<Integer> person_id = new ArrayList<Integer>();

    private SingleDataBase(){}
    public static SingleDataBase getInstance(){
        if(instance == null){
            instance = new SingleDataBase();
//            person_id = new ArrayList<int>();
        }

        return instance;
    }

    public void setVuz_id(int vuz_id) {
        this.vuz_id = vuz_id;
    }

    public int getVuz_id() {
        return vuz_id;
    }
    public void setSpec_id(int spec_id) {
        this.spec_id = spec_id;
    }
    public int getSpec_id() {
        return spec_id;
    }
    public void setTeacher_id(int person_id1){
        person_id.add(person_id1);
    }
    public ArrayList<Integer> getTeacher_id() {
        return person_id;
    }


}
