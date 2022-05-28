//집화처리 클래스
package realtimeCMS;
import java.util.*;
import java.lang.Thread;

class Define{//c언어의 #define과 같은 개념. 로켓 배송은 기본 지연시간 1, 아닌 배송은 2로 설정
    public static final int DefaultTime =2;
    public static final int RoketDefaultTime =1;
}

public class Classify extends Thread {
    public static Queue<ClassifyData> noneClassifiedOrders = new LinkedList<ClassifyData>();//처리가 지연된 주문들을 넣기 위해 임시 큐 구현
    public static ArrayList<ClassifyData> ClassifyingOrder = new ArrayList<ClassifyData>(4);//arraylist에 현재 분류작업중인 주문들을 넣는다.
    private final Object Delay = new Object();
    private ClassifyData element;
    ToolsForClassifying TFC;

    Classify() {
        Thread.currentThread().setName("Classify");
        Thread.currentThread().setPriority(4);
        TFC = new ToolsForClassifying();
    }

    public void AddTempOrders(Order order){//객체 생성과 동시에 객체의 지연시간 계산, 지연시간 계산 방법: 지연시간 =  기본 지연시간 1+ 수량/3 +무게/5
        int relatedtime = (order.rocketDelivery? Define.RoketDefaultTime : Define.DefaultTime) + (int)order.totalWeight/5;
        element=new ClassifyData(relatedtime,order);
        synchronized(Delay){
        noneClassifiedOrders.add(element);
        }
    }

    private void SendOrder(ArrayList<Order> sendOrders){
        synchronized(PickUp.Delay){
            PickUp.RecieveSpace.add(sendOrders);
        }
    }
    public void run(){
        ArrayList<Order> sendOrders;
        while(true){
           
            try{
                sendOrders = TFC.ClassifyOrders();
                 if(sendOrders.size()!=0){
                      SendOrder(sendOrders);
            }
            synchronized(Delay){
                TFC.ChangeState();
            }
                sleep(10000);
            }
            catch(InterruptedException e){return;}
        }


    }
} 

class ClassifyData{//기본 데이터 속성. 지연시간과 주문을 가짐.
    int relatedtime;
    Order order;
    ClassifyData(int relatedtime, Order order){
        this.relatedtime = relatedtime;
        this.order =order;
    }
}

class ToolsForClassifying{//분류작업 클래스ee
    
    private static ArrayList<Order> ReturnOrder = new ArrayList<Order>();

    public void ChangeState(){//큐가 빌때까지 분류작업에 대기중인 주문을 넘긴다. 만약 도중에 처리 용량(4)가 오버되면 반복문을 종료하고 나머지 주문은 그대로 큐에 남게 된다.
        boolean result;
        while(!Classify.noneClassifiedOrders.isEmpty()){
            result = TransferOrderToClassifying(Classify.noneClassifiedOrders.peek());
            if(result){
                Classify.noneClassifiedOrders.remove();
            }
            else{
                break;
            }
        }
    }

    public static boolean TransferOrderToClassifying(ClassifyData classifyData){//리스트의 용량이 초과되지 않는다면 데이터를 넣고 참값을 반환한다.
        if(Classify.ClassifyingOrder.size()!=4){
            Classify.ClassifyingOrder.add(classifyData);
            return true;
        }
        else
           return false;
    }
    
    public ArrayList<Order> ClassifyOrders(){//리스트에서 하나씩 꺼내 지연시간에 -1을 한 후, 지연시간이 0이면 반환할 리스트에 넣고 아니면 다시 분류작업 리스트에 넣는다. 모든 주문을
                                                           //다 돌면 리스트로 한꺼번에 반환한다.
        int index=0;
        ClassifyData temp; 
        if(ReturnOrder.size()!=0)
        ReturnOrder.clear();
        while(index<Classify.ClassifyingOrder.size()){
            temp=Classify.ClassifyingOrder.get(index);
            if(--temp.relatedtime==0){
                ReturnOrder.add(temp.order);
                Classify.ClassifyingOrder.remove(index);
            }
            index++;
            
        }
        return ReturnOrder;

    }
    }
    //추후 필요한 부분 더 추가할 예정...피곤해서 더 이상 코딩은 무리...









