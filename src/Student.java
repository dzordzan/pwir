import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	private boolean zdane;
	private int id;
	private String name;
	private int liczbaStudentow;
	Waiter waiter = new Waiter();

	public Student(int id) {
		name = losujImie();
		this.id = id;
	}

	private String losujImie() {

		return "Kowalski Marian";
	}

	public void run() {
		try {
			toCoStudenciZwykleRobiaWTrakcieSemestr();
			sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		zdane = false;
		while (zdane == false) {

			try {
				przegladajNotatki();
				zdawajEgzamin();
				zdane = sprawdzWynik();
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		toCoStudenciZwykleRobiaPoZdaniuEgzaminu();

	}

	private void toCoStudenciZwykleRobiaPoZdaniuEgzaminu() {
		System.out
				.println("[" + id + "]" + ". " + name + " Pije piwo po sesji");

	}

	private synchronized boolean sprawdzWynik() {
		int wynik;
		Random generator = new Random();
		int i = generator.nextInt(10) + 1;
		if (i >= 6) {
			wynik = 2;
			System.out.println("[" + id + "]" + ". " + name
					+ " nie zdal ocena: " + wynik);
			return false;
		} else {
			wynik = generator.nextInt(3) + 3;
			System.out.println("[" + id + "]" + ". " + name
					+ " zdal z wynikiem:" + wynik);
			return true;
		}
	}

	private synchronized void zdawajEgzamin() throws InterruptedException {
		System.out.println("[" + id + "]" + ". " + name
				+ " Podchodzi do egzaminu");
		sleep(1000);
	}

	private void przegladajNotatki() throws InterruptedException {
		System.out.println("[" + id + "]" + ". " + name + " Uczy sie" + "");
		;
		sleep(1000);
	}

	private void toCoStudenciZwykleRobiaWTrakcieSemestr() {
		System.out.println("[" + id + "]" + ". " + name + " Pije piwo");

	}

	public int getLiczbaStudentow() {
		return liczbaStudentow;
	}

	public void setLiczbaStudentow(int liczbaStudentow) {
		this.liczbaStudentow = liczbaStudentow;
	}

}
