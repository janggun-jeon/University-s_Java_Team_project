//집화처리한 물류를 트럭에 실어 다른 지역으로 발송하는 클래스
package realtimeCMS;
import java.util.*;



class Pickup{
    Area area = new Area();
    
    public void PickupOrder(ArrayList<Order> orders){
        int index=0;
        while(!orders.isEmpty()){
            Order dispatchorder = orders.get(index);
            AreaOrders AreaOrdersE=Area.contents.get(dispatchorder.address);
            if(AreaOrdersE.storage+dispatchorder.totalWeight<=area.maxstorage){
                AreaOrdersE.storage+=dispatchorder.totalWeight;
                AreaOrdersE.orders.add(dispatchorder);
                orders.remove(index);
            }
            else
            {
                WaitingOrder.stack.add(dispatchorder);
            }
            index++;
        }
    }

    public void PickupStack(){
        while(!WaitingOrder.stack.empty()){
            Order stackOrder = WaitingOrder.stack.pop();
            AreaOrders AreaOrdersE=Area.contents.get(stackOrder.address);
            if(AreaOrdersE.storage+stackOrder.totalWeight<=area.maxstorage){
                AreaOrdersE.storage+=stackOrder.totalWeight;
                AreaOrdersE.orders.add(stackOrder);
            }
            else{
                WaitingOrder.stack.add(0, stackOrder);
            }
        }
    }

    public void SendOrder(){
        AreaOrders order;
        for(String s: area.destination){
            order=Area.contents.get(s);
            if(order.storage>=area.maxstorage*0.7 && order.orders.size()>10){
                order.storage=0;
                order.orders.clear();
            }
        }  
        
    }
    }

    
 


class Area{  //택배 발송 지역 지정 클래스.  main에서 한번만 생성할 예정.
    public final int maxstorage = 1000;
    public static HashMap<String,AreaOrders > contents = new HashMap<String,AreaOrders>();
    public final String[] destination = {"지역1","지역2", "지역3", "지역4", "지역5", "지역6", "지역7", "지역8", "지역9"};//지역 종류는 나중에 지정
    Area(){
        for(String s: destination){
            contents.put(s,new AreaOrders());
        }  
    }
}

class AreaOrders{
    public int storage = 0;
    ArrayList<Order> orders = new ArrayList<Order>();
}

class WaitingOrder{//택배 용량이 부족하면 잠시 물류를 대기시키기 위해 만든 클래스. 원래라면 각 배송지별로 다르게 대기시켜야 하지만 일단은 하나의 스택에 적재 후 꺼낼 때가 되면 주문 내 배송지 정보를 통해 다시 찾아감
     public static Stack<Order> stack = new Stack<Order>();

}
