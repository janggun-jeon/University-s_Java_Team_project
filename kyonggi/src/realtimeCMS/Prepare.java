//집화처리 클래스
package realtimeCMS;
import java.util.*;

//대기 저장공간: hashmap("상품이름", "주문객체 레퍼런스")
//

class Define{//c언어의 #define과 같은 개념. 로켓 배송은 기본 지연시간 1, 아닌 배송은 2로 설정
    public static final int DefaultTime =2;
    public static final int RoketDefaultTime =1;
}

class PrepareData{//기본 데이터 속성. 지연시간과 주문을 가짐.
    int relatedtime;
    Order order;
}
public class Prepare{

    private static Queue<PrepareData> tem = new LinkedList<PrepareData>();//처리가 지연된 주문들을 넣기 위해 임시 큐 구현
    
    PrepareData element;//기본 데이터 속성 

    Prepare(Order order){//객체 생성과 동시에 객체의 지연시간 계산, 지연시간 계산 방법: 지연시간 =  기본 지연시간 1+ 수량/3 +무게/5
        element=new PrepareData();
        this.element.order = order;
        this.element.relatedtime= (order.rocketDelivery? Define.RoketDefaultTime : Define.DefaultTime) + (int)order.totalWeight/5;
        tem.add(this.element);
    }
    public void SwitchState(){//큐가 빌때까지 분류작업에 대기중인 주문을 넘긴다. 만약 도중에 처리 용량(4)가 오버되면 반복문을 종료하고 나머지 주문은 그대로 큐에 남게 된다.
        while(!tem.isEmpty()){
            if(!Classification.AddOrder(tem.remove()))
                break;          
        }
        //이 과정이 끝이 나면 상태가 변경된 상품 목록을 print하는 함수를 구현할 계획

    }
    
    }

class Classification{//분류작업 클래스ee
    
    private static ArrayList<PrepareData> ClassifyingOrder = new ArrayList<PrepareData>(4);//arraylist에 현재 분류작업중인 주문들을 넣는다.
    private static ArrayList<Order> ReturnOrder = new ArrayList<Order>();

    public static boolean AddOrder(PrepareData delayorder){//리스트의 용량이 초과되지 않는다면 데이터를 넣고 참값을 반환한다.
        if(ClassifyingOrder.size()!=4){
            ClassifyingOrder.add(delayorder);
            return true;
        }
        else
           return false;
    }
    
    public static ArrayList<Order> CompleteClassfication(){//리스트에서 하나씩 꺼내 지연시간에 -1을 한 후, 지연시간이 0이면 반환할 리스트에 넣고 아니면 다시 분류작업 리스트에 넣는다. 모든 주문을
                                                           //다 돌면 리스트로 한꺼번에 반환한다. 메인에서 이를 어떻게 사용할지 아직 잘 모르겠어서 일단은 이렇게 만들었습니다.
        int index=0;
        PrepareData temp; 
        if(ReturnOrder.size()!=0)
        ReturnOrder.clear();
        while(index<=ClassifyingOrder.size()){
            temp=ClassifyingOrder.remove(index);
            if(temp.relatedtime--==0) 
            ReturnOrder.add(temp.order);
            else{
                ClassifyingOrder.add(index,temp);
            }
            index++;
            
        }
        return ReturnOrder;

    }
    }
    //추후 필요한 부분 더 추가할 예정...피곤해서 더 이상 코딩은 무리...









