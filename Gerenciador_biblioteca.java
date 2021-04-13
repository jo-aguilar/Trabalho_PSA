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
}

class Brochura{
  protected String nome;
  protected String autor;
  protected String ISBN;
  protected boolean status_retirada;
  protected int quantidade_in_situ;
  protected int quantidade_disponivel;
}

//============================================================================//
//                                   SUBCLASSES                               //
//============================================================================//

class Usuario extends Individuo{
  public Usuario(String nn, String ss, boolean pr){
    super(nn, ss, pr);
  }
  public void setaPermissao(boolean pp){
    //modifica a permissão do Usuário para retirada ou não de livros
    permissao_retirada = pp;
  }
}

class Funcionario extends Individuo{
  public Funcionario(String nn, String ss){
    super(nn, ss, true);
  }

}



//============================================================================//
//                                     MAIN                                   //
//============================================================================//
public class Gerenciador_biblioteca{
  static public void main(String[]args){
  }
}
