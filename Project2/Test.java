class Test {
	
	public static void main(String[] args) {
		DLList<Character> list = new DLList<>();
		list.insertFirst('c');
		list.insertFirst('b');
		list.insertFirst('a');
		list.insertLast('e');
		list.insertLast('x');
		list.insertLast('g');
		list.seek(3);
		list.insertAtCurrent('d');
		list.seek(5);
		list.dataWrite('f');
		
		list.first();
		System.out.println(list.atFirst()); // Should print true
		
		// Should print a b c d e f g
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.dataRead());
			list.next();
		}
		
		DLList<Character> list1 = new DLList<>(list);
		list1.last();
		System.out.println(list1.atLast()); // Should print true
		
		// Should print g f e d c b a
		for(int i = 0; i < list1.size(); i++) {
			System.out.println(list1.dataRead());
			list1.previous();
		}
		
		list1.clear();
		System.out.println(list1.isEmpty()); // Should print true
		
		list.deleteFirst();
		list.deleteLast();
		list.seek(2);
		list.deleteAtCurrent();
		
		// Should print b c e f
		list.first();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.dataRead());
			list.next();
		}
	}
}