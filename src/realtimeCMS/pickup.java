package realtimeCMS;
import java.lang.String;
import java.util.*;


class AreaOrders {//�ù� �߼� ���� Ŭ������ ����� ���
    public int storage = 0;
    ArrayList<Order> orders = new ArrayList<Order>();
}


class Area{  //�ù� �߼� ���� ���� Ŭ����.  main���� �ѹ��� ������ ����.
    public static final int maxstorage = 25000;
    public static HashMap<String,AreaOrders > contents = new HashMap<String,AreaOrders>();
    public static String destination[] = {"����","������", "����", "����", "���", "����", "����", "����", "����", "����"}; //���� ������ ���߿� ����
    Area(){
        for (String s: destination) {
            contents.put(s,new AreaOrders());
        }  
    }
}

class WaitingOrder {//�ù� �뷮�� �����ϸ� ��� ������ ����Ű�� ���� ���� Ŭ����. ������� �� ��������� �ٸ��� �����Ѿ� ������ �ϴ��� �ϳ��� ���ÿ� ���� �� ���� ���� �Ǹ� �ֹ� �� ����� ������ ���� �ٽ� ã�ư�
    public static Stack<Order> stack = new Stack<Order>();
}

//�ֹ��� ���� �ù迡 ����. ���࿡ ���� Ʈ���� �뷮�� �ֹ��� ���Ը� ���� ���� �Ӱ谪�� �Ѵ´ٸ� ��� ����Ű������ �ֹ��� ���ÿ� ����
class Pickup {
    Area area = new Area();
    //���⼭ orders�� ��ȭ�ܰ� Ŭ�������� ó���� ������ ���� ����Ʈ�� �޴� �Ű� ��ü�Դϴ�
    public void PickupOrder(ArrayList<Order> orders){// Area Ŭ������ ���� �ù� �����͸� �ҷ��� �� ���� �񱳸� ���� ���� �ֹ����� areaŬ������ content�� ��������, ��� ���ÿ� �������� �Ǻ�
        int index=0;
        while(!orders.isEmpty()) {
            Order dispatchorder = orders.get(index);//�ε����� �÷����� �ֹ��� ������.
            AreaOrders AreaOrdersE=Area.contents.get(dispatchorder.address);//�ֹ��� �ּ� �Ӽ��� �̿��� �ؽ��ʿ��� �ش� �ּҸ� ã�� �� ���� ��ȯ
            if (AreaOrdersE.storage+dispatchorder.totalvolume<=Area.maxstorage) {//ũ�� ��
                AreaOrdersE.storage+=dispatchorder.totalvolume;
                AreaOrdersE.orders.add(dispatchorder);
            }
            else {
                WaitingOrder.stack.add(dispatchorder);
            }
            orders.remove(index);//�ֹ��� �Ű������Ƿ� ���� Ŭ������ ����� �ֹ��� ����
            index++;
        }
    }
    public void PickupStack() {//���ÿ� ���� �ֹ����� �ٽ� ������ pickuporder�� ����� ������� �����ϴ� �Լ�. ���࿡ ������ Ʈ���� �뷮�� ���ִٸ� �ش� �ֹ��� �ٽ� ������ ���ϴܿ� ����
        while (!WaitingOrder.stack.empty()) {
            Order stackOrder = WaitingOrder.stack.pop();
            AreaOrders AreaOrdersE=Area.contents.get(stackOrder.address);
            if (AreaOrdersE.storage+stackOrder.totalvolume<=Area.maxstorage) {
                AreaOrdersE.storage+=stackOrder.totalvolume;
                AreaOrdersE.orders.add(stackOrder);
            }
            else {
                WaitingOrder.stack.add(0, stackOrder);
            }
        }
    }
    public void SendOrder() {//�ù��� �뷮�� 70�ۼ�Ʈ�� ä������ �ֹ��� ������ 10���� �Ѵ´ٸ� ����. -> ���Ը� ���Ƿ� �ٲپ����Ƿ� �� ���ǵ� ���� �ٲ� ����
        AreaOrders order;
        for (String s: Area.destination) {
            order=Area.contents.get(s);
            if (order.storage>=Area.maxstorage*0.7 && order.orders.size()>10) {
                order.storage=0;//��������� 0���� �ϰ� �ʱ�ȭ
                order.orders.clear();
            }
        }  
    }
}

