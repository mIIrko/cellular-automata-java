package de.htwg.ca;

public class Application {
    
    public static void main(String[] args) throws InterruptedException {
	
	int width = 400;
	int rule = 90;
	int generations = 500;
	boolean wrapEdges = true;
	
	CellularAutomata ca = new CellularAutomata(width, rule, wrapEdges);
	
	for (int i = 0; i < generations; i++) {
	    Thread.sleep(70);
	    int[] state = ca.getCells();
	    for (int j: state) {
		System.out.print(j);
	    }
	    System.out.println();
	    ca.generate();
	}
    }
    
    

}
