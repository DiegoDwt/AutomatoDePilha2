package automatoDePilha;

import java.util.Stack;


public class Pilha {
	
	
	  private Stack<String> pilha;

	    public Pilha() {
	        pilha = new Stack<>();
	    }

	    public void empilha(String elemento) {
	        pilha.push(elemento);
	    }

	    public void desempilha() {
	        if (!estaVazia()) {
	            pilha.pop();
	        }
	    }

	    public String getTopo() {
	        if (!estaVazia()) {
	            return pilha.peek();
	        }
	        return null;
	    }

	    public boolean estaVazia() {
	        return pilha.isEmpty();
	    }

	    @Override
	    public String toString() {
	        return String.join(" ", pilha);
	    }
	
    public static void main(String[] args) {
        int[] F = {4};

        String[][] delta = {
            {"0", "ε", "Z0", "1", "Z0"},
            {"1", "ε", "Z0", "2", "Z0"},
            {"2", "ε", "Z0", "3", "Z0"},
            {"3", "ε", "Z0", "4", "ε"},
            {"1", "ε", "programa", "1", "id(<declaracao>) <comandos>"},
            {"1", "ε", "<comandos>", "1", "<comando> <comandos>"},
            {"1", "ε", "<comandos>", "1", "<comando>"},
            {"1", "ε", "<comando>", "1", "<declaracoes>"},
            {"1", "ε", "<comando>", "1", "if"},
            {"1", "ε", "<comando>", "1", "return"},
            {"1", "ε", "<comando>", "1", "<atribuicao>"},
            {"1", "ε", "<declaracoes>", "1", "id : <tipo>"},
            {"1", "ε", "<tipo>", "1", "char"},
            {"1", "ε", "<tipo>", "1", "int"},
            {"1", "ε", "<tipo>", "1", "vetor"},
            {"1", "ε", "if", "1", "if (<expressao>) { <comandos> } else { <comandos> }"},
            {"1", "ε", "<expressao>", "1", "id <op> <constante>"}
        };

        Pilha p = new Pilha();
        p.empilha("Z0");
        int estado = 0; // estado inicial
        String entrada = "programa id(id: int){ id: int; if (id > 0){ id = id / 2; } else{ id = 0; } return id;";

        int i = 0;

        while (i < entrada.length()) {
            System.out.println(p);
            String trans = null;
            for (String[] d : delta) {
                if (d[0].equals(Integer.toString(estado)) && d[1].equals(Character.toString(entrada.charAt(i))) && d[2].equals(p.getTopo())) {
                    trans = d[3];
                    break;
                }
            }
            if (trans == null) {
                break;
            }
            estado = Integer.parseInt(trans);
            p.desempilha();
            if (!trans.equals("ε")) {
                String[] elementos = trans.split(" ");
                for (int j = elementos.length - 1; j >= 0; j--) {
                    p.empilha(elementos[j]);
                }
            }
            i++;
        }

        if (contains(F, estado) && p.estaVazia()) {
            System.out.println("\nLinguagem Não Aceita");
        } else {
            System.out.println("\nLinguagem Aceita");
        }
    }

    private static boolean contains(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}
