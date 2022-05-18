package realtimeCMS;
import java.utils.*;
//대기 저장공간: hashmap("상품이름", "주문객체 레퍼런스")
//
public class Process{

    public static vector<ProcessItemE> tem = new vector<ProcessItemE>();
    
    ProcessItemE element = new ProcessItemE();

    Process(Order order){//객체 생성과 동시에 객체의 지연시간 계산, 지연시간 계산 방법: 지연시간 =  기본 지연시간 1+ 수량/3 +무게/5
        this.element.order = order;
        this.element.relatedtime= (order.rocketDelivery? 1 : 2) + order.totalWeight/5;
        tem.add(this.element);
    }
    }
}
class ProcessItemE extends Process{
    int relatedtime;
    Order order;
}



