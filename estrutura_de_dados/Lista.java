package Java_Projects.estrutura_de_dados;

public class Lista {
	protected No first;
	protected No last;
	protected int length;
	
	public void setFirst(No no) { 
		this.first = no; 
	}
	
	public void setLast(No no) { 
		this.last = no; 
	}
	
	public void setLength(int tam) { 
		this.length = tam;	
	}
	
	public No getFirst() { return first; }
	public No getLast() { return last; }
	public int getLength() { return length; }
	
	public void initiate() {
		setFirst(null); setLast(null); setLength(0);
	}
	
	protected boolean isEmpty() {
		return (first == null) ? true : false;
	}
	
	private void addFirst(int key) {
		No novo = new No(key);
		
		if(isEmpty())
			setLast(novo);
		else
			novo.setNext(first);
		
		setFirst(novo);
		length+=1;
	}
	
	private void addLast(int key) {
		No novo = new No(key);
		
		this.last.setNext(novo);
		setLast(novo);
		length+=1;
	}
	
	public void add(int key, int pos) {
		No novo = new No(key);
		No no = first;
		
		if(validPosition(pos)) {
			if(pos == 1 || isEmpty())
				addFirst(key);
			else if(pos-1 == length) 
				addLast(key);
			else {
				for(int i=1; i<pos-1; i++)
					no = no.getNext();
				novo.setNext(no.getNext());
				no.setNext(novo);
				length+=1;
			} 
		} else {
			System.out.printf("\nPosição %d inválida, chave %d não foi inserida.\n", pos, key);
			System.out.printf("\nPosição deve estar entre (1..%d)\n", length);
		}
	}
	
	private void removeFirst() {		
		No no = getFirst();
		
		if(no == last) { setLast(null); }
		
		setFirst(no.getNext());
		no = null;
		length-=1;
	}
	
	private void removeLast() {
		No last = getLast();
		No ant = getFirst();
		
		while(ant.getNext() != last) {
			ant = ant.getNext();
		}
		
		ant.setNext(null);
		setLast(ant);
		last = null;
		length-=1;
	}
	
	public int remove(int key) {
		No no = search(key);
		
		if(no==null || isEmpty()) 
			return 0;
		else {
			if(no.equals(this.first))
				removeFirst();
			else if(no.equals(this.last)) 
				removeLast();
			else {
				No ant = this.first;
				while(ant.getNext() != no) {
					ant = ant.getNext();
				}
				ant.setNext(no.getNext());
				no = null;
				length-=1;
			}
			return 1;
		}
	}
	
	public No search(int key) {
		boolean found=false;
		No no = first;
		
		for(int i=1; i<=length && !found; i++) {
			if(no.getKey() == key)
				found = true;
			else
				no = no.getNext();
		}
		
		if(!found) { return null; }
		else { return no; }
	}
	
	public void print() {
		No no = first;
		System.out.println("");
		while(no != null) {
			no.print();
			no = no.getNext();
			System.out.print(" -> ");
		}
		System.out.println("null");
		System.out.printf("\nTamanho: %d\n", this.length);
	}
	
	private boolean validPosition(int pos) {
		return (pos>0 && pos<=this.length+1) ? true : false;	
	}
	
	public void lineBreak() {
		System.out.println("______________________________________________________");
	}
	
	public static void main(String args[]) {
		Lista lista = new Lista();
		Pilha pilha  = new Pilha();
		Fila fila = new Fila();
		
		lista.initiate();
		pilha.initiate();
		fila.initiate();
		
		pilha.lineBreak();
		System.out.println("[PILHA]");
		pilha.add(1);
		pilha.add(2);
		pilha.add(3);
		pilha.add(4);
		pilha.remove();
		pilha.remove();
		pilha.remove();
		pilha.add(61);
		pilha.print();
		fila.search(61);
		
		fila.lineBreak();
		System.out.println("\n[FILA]");
		fila.add(1);
		fila.add(2);
		fila.add(3);
		fila.remove();
		fila.add(6);
		fila.remove();
		fila.add(7);
		fila.remove();
		fila.add(8);
		fila.print();
		fila.search(3);
		
		lista.lineBreak();
		System.out.println("\n[LISTA]");
		lista.add(1, 1);
		lista.add(2, 1);
		lista.add(7, 3);
		lista.add(10, 2);
		lista.remove(2);
		lista.remove(7);
		lista.add(52, 2);
		lista.add(60, 5);
		lista.print();
		lista.search(2);
	}
}
