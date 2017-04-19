package de.htwg.ca;

public class CellularAutomata {

    private int[] cells;
    private int[] ruleset;
    private boolean wrapEdges;

    public CellularAutomata(int width, int rule, boolean wrapEdges) {

	this.wrapEdges = wrapEdges;
	cells = new int[width];

	// insert the rules backward
	switch (rule) {
	case 90:
	    ruleset = new int[] { 0, 1, 0, 1, 1, 0, 1, 0 };
	    break;
	case 184:
	    ruleset = new int[] { 0, 0, 0, 1, 1, 1, 0, 1 };
	    break;
	default:
	    System.exit(-1);
	    ;
	    break;
	}

	for (int i = 0; i < cells.length; i++) {
	    cells[i] = 0;
	}
	// All cells start with state 0, except the center
	// cell has state 1.
	cells[cells.length / 2] = 1;
    }

    public void generate() {
	// [full] Compute the next generation.
	int[] nextgen = new int[cells.length];

	if (wrapEdges) {
	    for (int i = 0; i < cells.length; i++) {

		// in java we need a trick for calculating modulo of negative
		// numbers
		int left = cells[((((i - 1) % cells.length) + cells.length) % cells.length)];
		int me = cells[i];
		int right = cells[(i + 1) % cells.length];
		nextgen[i] = rules(left, me, right);
	    }
	} else {
	    for (int i = 1; i < cells.length - 1; i++) {
		int left = cells[i - 1];
		int me = cells[i];
		int right = cells[i + 1];
		nextgen[i] = rules(left, me, right);
	    }
	}

	// [end]
	cells = nextgen;
    }

    // [full] Look up a new state from the ruleset.
    public int rules(int a, int b, int c) {
	String s = "" + a + b + c;
	int index = Integer.parseInt(s, 2);
	return ruleset[index];
    }

    public int[] getCells() {
	return this.cells;
    }
}
