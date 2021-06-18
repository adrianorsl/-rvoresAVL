package ArvoreAVL;



public class ExerciciosAVL {

	static int x = 0;
	static int t = 0;
	static int y = 0;
	private static class ARVORE {
        public int num, altd, alte;
        public ARVORE dir, esq;
   }
    
    public static ARVORE inserir(ARVORE aux, int num) {
        // o objeto novo é um objeto auxiliar
        ARVORE novo;
        if (aux == null) {
            novo = new ARVORE();
            novo.num = num;
            novo.altd = 0;
            novo.alte = 0;
            novo.esq = null;
            novo.dir = null;
            aux = novo;
        } else if (num < aux.num) {
            aux.esq = inserir(aux.esq, num);
            if (aux.esq.altd > aux.esq.alte) {
                aux.alte = aux.esq.altd + 1;
            } else {
                aux.alte = aux.esq.alte + 1;
            }
            aux = balanceamento(aux);
        } else {
            aux.dir = inserir(aux.dir, num);
            if (aux.dir.altd > aux.dir.alte) {
                aux.altd = aux.dir.altd + 1;
            } else {
                aux.altd = aux.dir.alte + 1;
            }
            aux = balanceamento(aux);
        }
        return aux;
    }
    public static ARVORE balanceamento(ARVORE aux) {
        int d, df;
        d = aux.altd - aux.alte;
        if (d == 2) {
            df = aux.dir.altd - aux.dir.alte;
            if (df >= 0) {
                aux = rotacao_esquerda(aux);
            } else {
                aux.dir = rotacao_direita(aux.dir);
                aux = rotacao_esquerda(aux);
            }
        } else if (d == -2) {
            df = aux.esq.altd - aux.esq.alte;
            if (df <= 0) {
                aux = rotacao_direita(aux);
            } else {
                aux.esq = rotacao_esquerda(aux.esq);
                aux = rotacao_direita(aux);
            }
        }
        return aux;
    }
    
    public static ARVORE rotacao_esquerda(ARVORE aux) {
        ARVORE aux1, aux2;
        aux1 = aux.dir;
        aux2 = aux1.esq;
        aux.dir = aux2;
        aux1.esq = aux;
        if (aux.dir == null) {
            aux.altd = 0;
        } else if (aux.dir.alte > aux.dir.altd) {
            aux.altd = aux.dir.alte + 1;
        } else {
            aux.altd = aux.dir.altd + 1;
        }
        if (aux1.esq.alte > aux1.esq.altd) {
            aux1.alte = aux1.esq.alte + 1;
        } else {
            aux1.alte = aux1.esq.altd + 1;
        }
        return aux1;
    }

    public static ARVORE rotacao_direita(ARVORE aux) {
        ARVORE aux1, aux2;
        aux1 = aux.esq;
        aux2 = aux1.dir;
        aux.esq = aux2;
        aux1.dir = aux;
        if (aux.esq == null) {
            aux.alte = 0;
        } else if (aux.esq.alte > aux.esq.altd) {
            aux.alte = aux.esq.alte + 1;
        } else {
            aux.alte = aux.esq.altd + 1;
        }
        if (aux1.dir.alte > aux1.dir.altd) {
            aux1.altd = aux1.dir.alte + 1;
        } else {
            aux1.altd = aux1.dir.altd + 1;
        }
        return aux1;
    }
    
    public static void exibiremordem(ARVORE aux) {
        if (aux != null) {
            exibiremordem(aux.esq);
            System.out.print(aux.num + "  ");
            exibiremordem(aux.dir);
        }
    }
    
    
    // Questão 2 conta a altura dos nodos
    public static int altura(ARVORE aux) {
        if(aux == null || (aux.esq == null && aux.dir == null)) {
          return 0;
        }else {
        	if (altura(aux.esq) > altura(aux.dir))
        		return ( 1 + altura(aux.esq) );
        	else
        		return ( 1 + altura(aux.dir) );
        }
     }
    // Questão 2 verifica se é uma AVL
    public static void verificarAVL(ARVORE aux) {
    	int x = altura(aux.esq);
    	int y = altura(aux.dir);
    	int v = x - y;
    	if ((v >= -2) && (v <= 2)) {
    		System.out.println("\nÉ uma árvore AVL com altura de: " + v);
    	}else {
    		System.out.println("\nNão é uma árvore AVL com altura de: " + v);
    	}	
    }
    
    // Questão 3 excluir um elemento
    public static ARVORE excluir(ARVORE aux, int num) {
    	ARVORE aux2 = null;
    	if (aux.num == num) {
    		if (aux.dir == null && aux.esq == null) {
    			return null;
    		}else {
    			if (aux.esq != null && aux.dir == null) {
    				return aux.esq;
    			}
    			else if (aux.dir != null && aux.esq == null) {
    				return aux.dir;
    			}
    			else {
    				aux2 = aux.esq;
    				while (aux2.dir != null) {
    					aux2 = aux.dir;
    				}
    				aux.num = aux2.num;
    				aux2.num = num;
    				aux.esq = excluir(aux.esq, num);
    			}
    		}
    	}else if (num < aux.num) {
    		aux.esq = excluir(aux.esq, num);
    	}else if (num > aux.num) {
    		aux.dir = excluir(aux.dir, num);
    	}
		return aux;
    }
   // Questão 4
    public static int checar(ARVORE aux, ARVORE aux2) {
    	if (aux.num == aux2.num) {
    		if ((aux.dir == null && aux.esq == null) && (aux2.dir == null && aux2.esq == null)) {
    			y = y + 0;
    		}else {
    			if (aux.esq != null  && aux2.esq != null) {
    				checar(aux.esq, aux2.esq);
    			}else {
    				if (aux.esq != null) {
    					y = 1;		
    				}else if (aux2.esq != null) {
    					y = 1;   					
    				}
    			}
    			if (aux.dir != null && aux2.dir != null) {
    				checar(aux.dir, aux2.dir);
    			}else {
    				if (aux.dir != null) {
    					y = 1;
    					
    					
    				}else if (aux2.dir != null) {
    					y = 1;	
    				}
    			}
    		}
    		//Checar se a árvore AVL tem o mesmo formato com elementos diferentes
    	}else if (aux.num != aux2.num) {
    		if ((aux.dir == null && aux.esq == null) && (aux2.dir == null && aux2.esq == null)) {
    			y = y + 2;
    		}else {
    			if (aux.esq != null  && aux2.esq != null) {
    				checar(aux.esq, aux2.esq);
    			}else {
    				if (aux.esq != null) {
    					y = 1;		
    				}else if (aux2.esq != null) {
    					y = 1;   					
    				}
    			}
    			if (aux.dir != null && aux2.dir != null) {
    				checar(aux.dir, aux2.dir);
    			}else {
    				if (aux.dir != null) {
    					y = 1;	
    				}else if (aux2.dir != null) {
    					y = 1;	
    				}
    			}
    		}
    	}
		return y;
	
    }
	// Questão 4
    public static void checarAVL(ARVORE aux, ARVORE aux2) {
    	int x = checar(aux, aux2);
    	if (x == 0) {
    		System.out.println("Elementos iguais");
    	}else if (x == 1){
    		System.out.println("Elementos diferentes");
    	}else {
    		System.out.println("Árvore com formato igual, mas com elementos diferentes");
    	}
    }
    
 
   
    public static void main(String[] args) {
        ARVORE a = null;
        ARVORE b = null;
        
        a = inserir(a, 150);
        a = inserir(a, 100);
        a = inserir(a, 200);
        a = inserir(a, 220);
        a = inserir(a, 235);
        a = inserir(a, 240);
        
        b = inserir(b, 150);
        b = inserir(b, 100);
        b = inserir(b, 200);
        b = inserir(b, 220);
        b = inserir(b, 235);
        b = inserir(b, 238);


        exibiremordem(a);
       //excluir(a, 200);
       //exibiremordem(a);
        verificarAVL(a);
        System.out.println("\n");
        exibiremordem(b);
        verificarAVL(b);
        System.out.println("\n");
        checarAVL(a,b);
    }
    
    
}
