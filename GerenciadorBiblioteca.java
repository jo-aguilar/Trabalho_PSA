/*
Gerenciador para inserção de clientes de uma biblioteca.
Tipos de objetos:
Indivíduo (classe geral para pessoas)
Usuários:   acesso negado ao sistema mediante senha.
            Permissão para retirada de livros caso haja devolução e multas pagas.
Funcionário: acesso permitido ao sistema mediante senha.
             Permissão irrestrita de retirada de livros, inclusive revistas e
             atlas. Sem restrição para entrega de livros

Brochura (classe geral para encadernados)
Livro: retirada da biblioteca permitida
Revista: retirada da biblioteca proibida
Atlas: retirada da biblioteca proibida
*/

//============================================================================//
//                                 SUPERCLASSES                               //
//============================================================================//
class Individuo{
  protected String nome;
  protected String status;   // status/cargo do indivíduo mediante a instituição
  protected boolean permissao_retirada;
  public Individuo(String nn, String ss, boolean pr){
    nome = nn;
    status = ss;
    permissao_retirada = pr;
  }
  String retornaNome(){return nome;}
  String retornaStatus(){return status;}
  boolean retornaPermissao(){return permissao_retirada;}
}

class Brochura{
   protected String nome;
   protected String autor;
   protected String ISBN;
   protected int quantidade_in_situ;
   protected int quantidade_disponivel;
   public Brochura(String nn, String aut, int qtd){
      nome = nn;
      autor = aut;
      //ISBN = gera_isbn();
      quantidade_in_situ = qtd;
      quantidade_disponivel = qtd;
   }
   public String retornaNome(){return nome;}
   public String retornaAutor(){return autor;}
   public String retornaISBN(){return ISBN;}
   public int retornaInSitu(){return quantidade_in_situ;}
   public int retornaDisponivel(){return quantidade_disponivel;}
}

//============================================================================//
//                                   SUBCLASSES                               //
//============================================================================//

class Usuario extends Individuo{
   private float valor_multa;
   public Usuario(String nn, String ss, boolean pr){
       super(nn, ss, pr);
   }
   public void setaPermissao(boolean pp){
     //modifica a permissão do Usuário para retirada ou não de livros, pois,
     //diferente de funcionários, eles não têm acesso irrestrito aos livros
     permissao_retirada = pp;
   }
   public void setaMulta(tempo){
      //fórmula para computação da multa;
   }
   public float retornaMulta(){return valor_multa;}
}


class Funcionario extends Individuo{
   public Funcionario(String nn, String ss){
   //terceiro argumento de super em true implia em funcionários terem acesso
   //irrestrito aos livros da biblioteca, sem necessidade de manutenção
   super(nn, ss, true);
   }
}



//============================================================================//
//                                     MAIN                                   //
//============================================================================//
public class GerenciadorBiblioteca{
  static public void main(String[]args){
  }
}
