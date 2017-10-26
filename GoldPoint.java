package e1;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GoldPoint {

public static void main(String[] args){
    GoldPoint gd=new GoldPoint();
    gd.goldPoint();
}
public void goldPoint(){
    HashMap<String,Double> inputMap=new HashMap<String,Double>();//存入输入分数
    HashMap<String,Double> scoreMap=new HashMap<String,Double>();//存入分数
    String name="";
    Double inputScore;
    int peopleNum;//参加的人数
    int time;//进行轮数
    Double sum=0.0;
    Double aver=0.0;
    Scanner scan=new Scanner(System.in); //参数对象是系统进来的流
    System.out.println("输入参加的人数：");
    peopleNum=scan.nextInt();
    System.out.println("输入需要进行几轮：");
    time=scan.nextInt();
    for(int i=0;i<peopleNum;i++){
        System.out.println("请输入第"+(i+1)+"个参加者的姓名：");
        name=scan.next();
        System.out.println("请输入第一轮的分数：");
        inputScore=scan.nextDouble();
         inputMap.put(name, inputScore);
         scoreMap.put(name,(double) 0);//初始化scoreMap
         sum+=inputScore;
    }
    aver=sum/peopleNum*0.618;
    System.out.println("aver="+aver);
    this.findWinner(inputMap, scoreMap, aver);
    this.show(scoreMap);
    System.out.println("第一轮结束");
    for(int i=0;i<time-1;i++){
            sum=0.0;
            System.out.println("请输入第"+(i+2)+"轮的分数：");
            Iterator iter = inputMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry0 = (Map.Entry) iter.next();
                String key = (String) entry0.getKey();
                System.out.println(key+"输入第"+(i+2)+"轮分数：");
                Double score =scan.nextDouble();
                inputMap.put(key, score);//替换掉以前的分数
                sum+=score;
        }
            aver=sum/peopleNum*0.618;
            System.out.println("aver="+aver);
            this.findWinner(inputMap, scoreMap, aver);
            this.show(scoreMap);
        System.out.println("第"+(i+2)+"轮结束");
    } 
}
//找出每次分数最接近黄金点的 和最远的 最接近的加一分 最远的减一分 其余加零分（可能有相同的）
public void findWinner(HashMap<String,Double> inputMap,HashMap<String,Double> scoreMap,Double aver){   
    Double temp;
    Double temp0;
    List<String> latest=new ArrayList<String>();
    List<String> farthest=new ArrayList<String>();
     
    Iterator iter = inputMap.entrySet().iterator();
    Map.Entry entry = (Map.Entry) iter.next();
    Double input = (Double) entry.getValue();
    String key0 = (String) entry.getKey();
    latest.add(key0);
    farthest.add(key0);
    //iter.hasNext();
    temp0=temp=Math.abs(aver-input);
    //遍历map
    while (iter.hasNext()) {   
        entry = (Map.Entry) iter.next();
        String key = (String) entry.getKey();
        input = (Double) entry.getValue();
        Double temp1=Math.abs(aver-input);
        if(temp>temp1){//寻找最近
            temp=temp1;
             latest.clear();
             latest.add(key);
        }else if(temp==temp1){
            latest.add(key);
        }
        if(temp0<temp1){//寻找最远
            temp0=temp1;
            farthest.clear();
            farthest.add(key);}
        else if(temp0==temp1){
            farthest.add(key);
        }
    }
    //实现加分
    iter = scoreMap.entrySet().iterator();
    while (iter.hasNext()) {
        Map.Entry entry0 = (Map.Entry) iter.next();
        String key = (String) entry0.getKey();
        Double score =(Double) entry0.getValue();
        if(this.containList(key, latest)){
            score=score+1;
            scoreMap.put(key, score);
            }
        if(this.containList(key, farthest)){
            score=score-1;
            scoreMap.put(key, score);
        }
        }
}
public boolean containList(String str,List<String> list){
    for(int i=0;i<list.size();i++){
        if(str.equals(list.get(i))){
            return true;
        }
    }
    return false;
}
public void show(HashMap<String,Double> scoreMap){
    System.out.println("得分情况：");
    Iterator iter = scoreMap.entrySet().iterator();
    while (iter.hasNext()) {
        Map.Entry entry0 = (Map.Entry) iter.next();
        String key = (String) entry0.getKey();
        Double score =(Double) entry0.getValue();
        System.out.println(key+"："+score);
    }
}
 
}
