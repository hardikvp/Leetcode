import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class AddTwoNumbers {
	private static List<Integer> a = new LinkedList<Integer>();
	static int carry = 0;
	static int value;
	
	public static void addition (LinkedList<Integer> b,LinkedList<Integer> c ) {
		ListIterator<Integer> listIterator = b.listIterator();
		ListIterator<Integer> listIterator1 = c.listIterator();
		while (listIterator.hasNext() && listIterator1.hasNext()) {
			int addition = listIterator.next() + listIterator1.next() + carry;
			if(addition >= 10) {
				carry = (addition) / 10;
				value = (addition) % 10;
				a.add(value);
			} else {
				a.add(addition);
			}
			
		}
		while(listIterator.hasNext()) {
			a.add(listIterator.next());
		}
		while(listIterator1.hasNext()) {
			a.add(listIterator1.next());
		}
		ListIterator<Integer> l2 = a.listIterator();
		while(l2.hasNext()) {
			System.out.print(l2.next() + "->");
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		linkedList.add(2);
		linkedList.add(4);
		linkedList.add(3);
		LinkedList<Integer> linkedList1 = new LinkedList<Integer>();
		linkedList1.add(1);
		addition(linkedList,linkedList1);
		

	}

}
