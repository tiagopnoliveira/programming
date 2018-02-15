import java.util.LinkedList;

public class AnimalShelter {
	public static class Animal {
		String name;
		char type;
		
		public Animal(String name, char type) {
			this.name = name;
			this.type = type;
		}
	}
	public static class Shelter {
		LinkedList<Animal> q = new LinkedList<Animal>();
		
		public void enqueue(Animal a) {
			q.add(a);
		}
		
		public Animal dequeueAny() {
			return q.poll();
		}
		
		private Animal dequeueByType(char t) {
			for(Animal a : q) {
				if(a.type == t) {
					q.remove(a);
					return a;
				}
			}
			return null;		
		}

		public Animal dequeueCat() {
			return dequeueByType('c');
		}

		public Animal dequeueDog() {
			return dequeueByType('d');
		}
	}

	//private static int[] a = {1,2,3,4};
    public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		
		Shelter s = new Shelter();
		Animal a;
		a = new Animal("c1", 'c');
		s.enqueue(a);
		a = new Animal("c2", 'c');
		s.enqueue(a);
		a = new Animal("d1", 'd');
		s.enqueue(a);
		a = new Animal("c3", 'c');
		s.enqueue(a);
		a = new Animal("d2", 'd');
		s.enqueue(a);
		a = new Animal("c4", 'c');
		s.enqueue(a);
		a = new Animal("d3", 'd');
		s.enqueue(a);
		a = new Animal("d4", 'd');
		s.enqueue(a);
		
		System.out.println(s.dequeueAny().name);
		System.out.println(s.dequeueCat().name);
		System.out.println(s.dequeueCat().name);
		System.out.println(s.dequeueDog().name);
		System.out.println(s.dequeueAny().name);
		System.out.println(s.dequeueDog().name);

		double duration = System.currentTimeMillis() - startTime;
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");
    }
}
