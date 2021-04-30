import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;


class JanelaTrabalho extends Frame{
   //listas para a manipulação de dados do programa
   ArrayList<Brochura> livros = new ArrayList<Brochura>();
   ArrayList<Individuo> usuarios = new ArrayList<Individuo>();

   private String sep = "________________________________________________________________________________";
   private Checkbox cadastro_livro, cadastro_pessoa, retira_volume, consulta_pessoa,
                    atlas, livro, revista;
   private CampoTexto nome_livro, autor_livro, quantidade,
           nome_usuario, rg,
           requerente, retirado,
           nome_usuario2, rg2;
   private Separador separador, separador2, separador3;

   public JanelaTrabalho(String nome){

      super(nome);
      addWindowListener(new WindowAdapter(){
          public void windowClosing(WindowEvent e){
             dispose();
          }
      });

      Botao veVolumes   = new Botao(490, 50, 30, 30, "L", ()->{
          Frame janela = new Frame("Livros Cadastrados");
          TextArea lista_livros = new TextArea();
          String saida = "";

          janela.addWindowListener(new WindowAdapter(){
              public void windowClosing(WindowEvent e){
                 janela.dispose();
              }
          });

          for(int clk = 0; clk < livros.size(); clk++){
            saida = saida + String.format("%-21s\t| %-19s\t| %-12s| %-3d| %-3d\n",
                  livros.get(clk).retornaNome(), livros.get(clk).retornaAutor(),
                  livros.get(clk).retornaISBN(), livros.get(clk).retornaDisponivel(),
                  livros.get(clk).retornaInSitu());
          }
          System.out.println(saida);
          lista_livros.setText(saida);

          janela.setSize(530, 250);
          lista_livros.setBounds(20, 20, 510, 250);
          janela.add(lista_livros);
          janela.setVisible(true);
      });

      Botao veIndividuos      = new Botao(525, 50, 30, 30, "U", ()->{
        Frame janela = new Frame("Usuários Cadastrados");
        TextArea lista_usuarios = new TextArea();
        String saida = "";

        janela.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
               janela.dispose();
            }
        });

        for(int clk = 0; clk < usuarios.size(); clk++){
          saida = saida + String.format("%-20s\t | %-12s\t | %-13s\t | %3s\n",
                usuarios.get(clk).retornaNome(), usuarios.get(clk).retornaRG(),
                usuarios.get(clk).retornaStatus(),
                (usuarios.get(clk).retornaPermissao()==true?"S":"N"));
        }
        System.out.println(saida);
        lista_usuarios.setText(saida);

        janela.setSize(350, 250);
        lista_usuarios.setBounds(20, 20, 320, 250);
        janela.add(lista_usuarios);
        janela.setVisible(true);
      });

      add(veIndividuos);
      add(veVolumes);

      CheckboxGroup gp = new CheckboxGroup();
      cadastro_livro   = new Checkbox("Cadastro de Volumes", gp, true);
      cadastro_pessoa  = new Checkbox("Cadastro de Pessoas", gp, false);
      retira_volume    = new Checkbox("Retirada/Entrega de Volumes", gp, false);
      consulta_pessoa  = new Checkbox("Consulta Pessoal", gp, false);
      cadastro_livro.setBounds(20, 60, 200, 25);
      cadastro_pessoa.setBounds(20, 190, 200, 25);
      retira_volume.setBounds(20, 340, 200, 25);
      consulta_pessoa.setBounds(20, 470, 200, 25);

      //===================================================================================//
      //                         Campo para inserção de volumes                            //
      //===================================================================================//

      nome_livro  = new CampoTexto(20, 90, 400, 25, "Nome");
      autor_livro = new CampoTexto(20, 120, 250, 25, "Autor");
      quantidade  = new CampoTexto(350, 120, 70, 25, "Qtde");
      CheckboxGroup gp2 = new CheckboxGroup();
      atlas   = new Checkbox("Atlas", gp2, true);    atlas.setBounds(90, 150, 70, 25);
      livro   = new Checkbox("Livro", gp2, false);   livro.setBounds(230,150, 70, 25);
      revista = new Checkbox("Revista", gp2, false); revista.setBounds(350, 150, 70, 25);
      separador = new Separador(20, 170 ,sep);

      Botao adicionaVolume   = new Botao(470, 100, 80, 40, "Adicionar", ()->{
        if(cadastro_livro.getState()==true){
          if(!nome_livro.getText().equals("")  &&
             !autor_livro.getText().equals("") &&
             !quantidade.getText().equals("")){
               if(atlas.getState()==true)
                 livros.add(new Atlas(nome_livro.getText(), autor_livro.getText(), Integer.parseInt(quantidade.getText())));
               else if(livro.getState()==true)
                 livros.add(new Livro(nome_livro.getText(), autor_livro.getText(), Integer.parseInt(quantidade.getText())));
               else if(revista.getState()==true)
                 livros.add(new Revista(nome_livro.getText(), autor_livro.getText(), Integer.parseInt(quantidade.getText())));
               nome_livro.setText("");
               autor_livro.setText("");
               quantidade.setText("");

               for(Brochura n: livros)
                  mostra_brochura(n);
             }
             else{
               JOptionPane.showMessageDialog(null, "Dados insuficientes\nVerifique os dados e tente novamente");
               nome_livro.setText("");
               autor_livro.setText("");
               quantidade.setText("");
             }
        }

      });

      add(cadastro_livro);
      add(cadastro_pessoa);
      add(quantidade);
      add(retira_volume);
      add(consulta_pessoa);
      add(nome_livro);
      add(nome_livro.retornaEtiqueta());
      add(autor_livro);
      add(autor_livro.retornaEtiqueta());
      add(quantidade.retornaEtiqueta());
      add(atlas); add(livro); add(revista);
      add(adicionaVolume);
      add(separador);
      adicionaVolume.setVisible(true);

      //===================================================================================//
      //                        Campo para cadastro de indivíduos                          //
      //===================================================================================//

      CheckboxGroup gp3 = new CheckboxGroup();
      Checkbox adicionar = new Checkbox("Adicionar", gp3, true);
      Checkbox remover   = new Checkbox("Remover", gp3, false);

      CheckboxGroup gp4 = new CheckboxGroup();
      Checkbox usuario = new Checkbox("Usuário", gp4, true);
      Checkbox funcionario = new Checkbox("Funcionário", gp4, false);
      nome_usuario = new CampoTexto(20, 240, 400, 25, "Nome");
      rg           = new CampoTexto(20, 270, 150, 25, "RG  ");

      usuario.setBounds(150, 300, 100, 20);
      funcionario.setBounds(300, 300, 100, 20);
      adicionar.setBounds(150, 220, 100, 20);
      remover.setBounds(300, 220, 100, 20);
      separador2 = new Separador(20, 320, sep);

      Botao executarCadastro = new Botao(470, 250, 80, 40, "Executar", ()->{
        if(cadastro_pessoa.getState()==true){
          if(adicionar.getState()==true){
            if(!nome_usuario.getText().equals("")  &&
               !rg.getText().equals("")){
                 if(usuario.getState()==true)
                   usuarios.add(new Usuario(nome_usuario.getText(), Integer.parseInt(rg.getText())));
                 else if(funcionario.getState()==true)
                   usuarios.add(new Funcionario(nome_livro.getText(), Integer.parseInt(rg.getText())));
                 nome_usuario.setText("");
                 rg.setText("");

                 for(Individuo n: usuarios)
                    mostra_individuo(n);
                 System.out.println();
               }
               else{
                 JOptionPane.showMessageDialog(null, "Dados insuficientes\nVerifique os dados e tente novamente");
                 nome_usuario.setText("");
                 rg.setText("");
               }
          }
          else if (remover.getState()==true){
            if(!nome_usuario.getText().equals("") && !rg.getText().equals("")){
              JOptionPane.showMessageDialog(null, "Forneça penas o nome ou apenas o RG\nPara efetuar remoção de usuário");
              nome_usuario.setText("");
              rg.setText("");
            }
            else{
              if(!nome_usuario.getText().equals("")){
                  for(int clk = 0; clk < usuarios.size(); clk++){
                    if((usuarios.get(clk).retornaNome()).equals(nome_usuario.getText())){
                      usuarios.remove(clk);
                      nome_usuario.setText("");
                      JOptionPane.showMessageDialog(null, "Usuário Removido");

                      for(Individuo n: usuarios)
                         mostra_individuo(n);
                      System.out.println();
                      return;
                      }
                    }
                  JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                  nome_usuario.setText("");
              }
              else if(!rg.getText().equals("")){
                System.out.printf("Número entregue %d\n", Integer.parseInt(rg.getText()));
                for(int clk = 0; clk < usuarios.size(); clk++){
                  System.out.printf("RG do usuário #%d: %d\n", clk+1, usuarios.get(clk).retornaRGn());
                  if(usuarios.get(clk).retornaRGn()==Integer.parseInt(rg.getText())){
                    usuarios.remove(clk);
                    JOptionPane.showMessageDialog(null, "Usuário Removido");
                    rg.setText("");

                    for(Individuo n: usuarios)
                       mostra_individuo(n);
                    System.out.println();
                    return;
                  }//fim de if()
                }//fim de for()
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                rg.setText("");
            }//fim de else if()
            }
          }
        }
      });

      add(nome_usuario);
      add(rg);
      add(nome_usuario.retornaEtiqueta());
      add(rg.retornaEtiqueta());
      add(usuario);
      add(funcionario);
      add(adicionar);
      add(remover);
      add(executarCadastro);
      add(separador2);
      executarCadastro.setVisible(true);

      //===================================================================================//
      //                     Campo para retirada/devolução de volumes                      //
      //===================================================================================//

      CheckboxGroup gp5 = new CheckboxGroup();
      Checkbox retirada = new Checkbox("Retirada", gp5, true);
      Checkbox devolucao = new Checkbox("Devolução", gp5, false);

      requerente = new CampoTexto(20, 390, 400, 25, "Nome");
      retirado   = new CampoTexto(20, 420, 380, 25, "Volume");

      Botao botaoGerenciar   = new Botao(470, 400, 80, 40, "Gerenciar", ()->{
        if(retira_volume.getState()==true){
          final int indice_usuario = devolve_indice_usuario(usuarios, requerente.getText());
          final int indice_volume = devolve_indice_volume(livros, retirado.getText());
          if(indice_usuario==-1){
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
            requerente.setText("");
            retirado.setText("");
            return;
          }
          else if(indice_volume==-1){
            JOptionPane.showMessageDialog(null, "Volume não cadastrado");
            requerente.setText("");
            retirado.setText("");
            return;
          }

          if(retirada.getState()==true){
            livros.get(indice_volume).diminuiInSitu();
            ((Usuario)usuarios.get(indice_usuario)).adicionaLivro((Livro)(livros.get(indice_volume)));
            for(Brochura n: livros)
               mostra_brochura(n);
            System.out.println();
          }
          else if(devolucao.getState()==true){
            final int livro_em_posse = devolve_indice_volume(((Usuario)usuarios.get(indice_usuario)).retornaLista(), retirado.getText());
            if(livro_em_posse == -1){
              JOptionPane.showMessageDialog(null, "Volume não está em posse do usuário");
              requerente.setText("");
              retirado.setText("");
              return;
            }

            livros.get(indice_volume).aumentaInSitu();
            ((Usuario)usuarios.get(indice_usuario)).removeLivro((Livro)(livros.get(indice_volume)));
            for(Brochura n: livros)
               mostra_brochura(n);
            System.out.println();
          }
        }
      });

      retirada.setBounds(150, 370, 100, 20);
      devolucao.setBounds(300, 370, 100, 20);
      separador3 = new Separador(20, 450 ,sep);

      add(retirada);
      add(devolucao);
      add(requerente);
      add(retirado);
      add(requerente.retornaEtiqueta());
      add(retirado.retornaEtiqueta());
      add(botaoGerenciar);
      add(separador3);
      botaoGerenciar.setVisible(true);

      //===================================================================================//
      //                     Campo para pesquisar pendências                               //
      //===================================================================================//

      CheckboxGroup gp6 = new CheckboxGroup();
      Checkbox nome_tag = new Checkbox("Nome", gp3, true);
      Checkbox rg_tag   = new Checkbox("RG  ", gp3, false);
      TextArea busca = new TextArea("");
      busca.setBounds(20, 555, 530, 130);

      nome_usuario2    = new CampoTexto(20, 520, 230, 25, "Nome");
      rg2              = new CampoTexto(270, 520, 150, 25, "RG  ");

      Botao botaoPesquisar   = new Botao(470, 510, 80, 40, "Consultar", ()->{
        if(consulta_pessoa.getState()==true){
          System.out.println("DEBUG 1");
          if(nome_usuario.getText().equals(" ") && rg2.getText().equals(" ")){
            JOptionPane.showMessageDialog(null, "Impossível executar ação. \n" +
                                          "Por favor, forneça dados válidos");
          }
          if(nome_tag.getState()==true){
            if(!nome_usuario2.getText().equals(" ")){
                System.out.println("DEBUG 2");
              ArrayList<Brochura> lista = new ArrayList<Brochura>();
              String saida = "";
              final int indice_usuario = devolve_indice_usuario(usuarios, nome_usuario2.getText());
              System.out.printf("Nome do usuário: %s\n", nome_usuario2.getText());
              if(indice_usuario==-1){
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                return;
              }
              else if(indice_usuario != -1){

                lista = ((Usuario)usuarios.get(indice_usuario)).retornaLista();
                for(int clk = 0; clk < lista.size(); clk++){
                  saida = saida + String.format("%21s\t| %19s\t| %12s| %3d| %3d|\n",
                        lista.get(clk).retornaNome(), lista.get(clk).retornaAutor(),
                        lista.get(clk).retornaISBN(), lista.get(clk).retornaDisponivel(),
                        lista.get(clk).retornaInSitu());
                }
                System.out.println(saida);
                busca.setText(saida);
              }
            }
          }//fim do if() principal
          else if(rg_tag.getState()==true){
            if(!rg2.getText().equals(" ")){
                System.out.println("DEBUG 2");
              ArrayList<Brochura> lista = new ArrayList<Brochura>();
              String saida = "";
              final int indice_usuario = devolve_indice_usuario_rg(usuarios, Integer.parseInt(rg2.getText()));
              System.out.printf("Nome do usuário: %s\n", nome_usuario2.getText());
              if(indice_usuario==-1){
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                return;
              }
              else if(indice_usuario != -1){

                lista = ((Usuario)usuarios.get(indice_usuario)).retornaLista();
                for(int clk = 0; clk < lista.size(); clk++){
                  saida = saida + String.format("%21s\t| %19s\t| %12s| %3d| %3d|\n",
                        lista.get(clk).retornaNome(), lista.get(clk).retornaAutor(),
                        lista.get(clk).retornaISBN(), lista.get(clk).retornaDisponivel(),
                        lista.get(clk).retornaInSitu());
                }
                System.out.println(saida);
                busca.setText(saida);
              }
            }
          }
        }
      });

      nome_tag.setBounds(150, 500, 100, 20);
      rg_tag.setBounds(350, 500, 100, 20);

      add(nome_tag);
      add(rg_tag);
      add(nome_usuario2);
      add(rg2);
      add(nome_usuario2.retornaEtiqueta());
      add(rg2.retornaEtiqueta());
      botaoPesquisar.setVisible(true);
      add(botaoPesquisar);
      busca.setVisible(true);
      add(busca);

   }

   static int devolve_indice_volume(ArrayList<Brochura> lista, String volume){
        for(int clk = 0; clk < lista.size(); clk++){
          if(lista.get(clk).retornaNome().equals(volume))
            return clk;
        }
        return -1;
   }

   static int devolve_indice_usuario_rg(ArrayList<Individuo> lista, int rg){
     for(int clk = 0; clk < lista.size(); clk++){
       if(lista.get(clk).retornaRGn()==rg){
         return clk;
       }
     }
     return -1;
   }

   static int devolve_indice_usuario(ArrayList<Individuo> lista, String nome){
      for(int clk = 0; clk < lista.size(); clk++){
        if(lista.get(clk).retornaNome().equals(nome))
          return clk;
      }
      return -1;
   }


   static void mostra_brochura(Brochura bro){
      //teste para verificar a integridade do objeto Brochura
      /*Entrega os dados das brochuras de acordo com o que foi catalogado
        no módulo de catálogo para brochuras*/
      System.out.printf("%21s| %19s| %12s| %3d| %3d|\n", bro.retornaNome(), bro.retornaAutor(),
            bro.retornaISBN(), bro.retornaDisponivel(),
            bro.retornaInSitu());
   }

   static void mostra_individuo(Individuo ind){
      //teste para verificar a integridade do objeto Individuo
      /*Entrega os dados dos usuários de acordo com o que foi catalogado
        no múdulo de catálogo para indivíduos (tanto funcionários quanto clientes)*/
      System.out.printf("%20s | %12s | %13s | %3s|\n", ind.retornaNome(), ind.retornaRG(),
            ind.retornaStatus(),
            (ind.retornaPermissao()==true?"S":"N"));
   }

   //##################################################################################//
   //                                        MAIN                                      //
   //##################################################################################//


   static public void main(String[] args){

      JanelaTrabalho janela = new JanelaTrabalho("Gerenciador de Fluxo de Volumes");
      janela.setSize(600, 700);
      janela.setLayout(null);
      janela.setResizable(false);
      janela.setVisible(true);
   }

   //##################################################################################//
   //                                FIM DE MAIN                                       //
   //##################################################################################//

}

//============================================================================//
//                                 SUPERCLASSES                               //
//============================================================================//

class Individuo{
   /*Objeto básico para a criação de objetos relacionados aos indivíduos com algum
     vínculo com a biblioteca, podendo ser de usuários registrados a funcionários do
     local*/
   protected String nome;
   protected int rg;
   protected String status;   // status/cargo do indivíduo mediante a instituição
   protected boolean permissao_retirada;
   public Individuo(String nn, int rrgg, String ss, boolean pr){
      nome = nn;
      rg = rrgg;
      status = ss;
      permissao_retirada = pr;
   }
   String retornaNome(){return nome;}
   String retornaStatus(){return status;}
   boolean retornaPermissao(){return permissao_retirada;}
   String retornaRG(){
      //retorna o RG em forma de uma String legível sistematicamente
      String auxiliar = Integer.toString(rg);
      return (auxiliar.substring(0, 8) + "-" + auxiliar.substring(8,9));
   }
   int retornaRGn(){ return rg;}//retorna a versão numérica do RG
}



class Brochura{
   /*Objeto básico para a criação de objetos relacionados aos livros e análogos ca-
     talogados na biblioteca, podendo ser de revistas e livros e atlas para consulta */
   protected boolean retirada; //determina se a brochura pode ser retirada
   protected boolean devolucao;//determina se a brochura deve ser devolvida
   protected String nome;
   protected String autor;
   protected String ISBN;
   protected int quantidade_in_situ;
   protected int quantidade_disponivel;
   public Brochura(String nn, String aut, int qtd, boolean ret, boolean dev){
      nome = nn;
      autor = aut;
      quantidade_in_situ = qtd;
      quantidade_disponivel = qtd;
      ISBN = geraISBN().toString();
      retirada = ret;
      devolucao = dev;
   }
   public String retornaNome(){return nome;}
   public String retornaAutor(){return autor;}
   public String retornaISBN(){return ISBN;}
   public int retornaInSitu(){return quantidade_in_situ;}
   public int retornaDisponivel(){return quantidade_disponivel;}
   public boolean retornaRetirada(){return retirada;}
   public boolean retornaDevolucao(){return devolucao;}
   public void setaISBN(String isbn){ISBN = isbn;}
   public void diminuiInSitu(){
      //caso ainda haja livros in situ, diminuir em um volume (retirada)
      if(quantidade_in_situ>0)
         quantidade_in_situ--;
      else
         return;
      }
   public void aumentaInSitu(){
      //caso nem todos os livros estejam in situ, aumentar em um volume (entrega)
      if(quantidade_in_situ<quantidade_disponivel)
         quantidade_in_situ++;
      else
         return;
   }

   private StringBuilder geraISBN(){
      /*Gera um registro aleatório de ISBN para que uma brochura qualquer possa
        ser catalogada no sistema  de livros*/
      StringBuilder resultado = new StringBuilder();
      Random rand = new Random();
      for(int clk = 0; clk < 13; clk++){
         if(clk==4 || clk==6 || clk==11)
            resultado.append("-");
         else
            resultado.append(Integer.toString(rand.nextInt(9)));
      }
      return resultado;
   }
}


//============================================================================//
//                                   SUBCLASSES                               //
//============================================================================//


////////////////////////////// SUBCLASSES DE INDIVIDUO /////////////////////////
class Usuario extends Individuo{
   private float valor_multa;
   private int dias_excedentes;
   private ArrayList<Brochura>livros_retirados = new ArrayList<Brochura>();
   //lista com as brochuras retiráveis alocadas pelo usuário
   //implica que revistas não são computadas (retirada permanente)
   //e atlas não são retiráveis
   public Usuario(String nn, int rrgg){
      super(nn, rrgg, "usuário", true);
      valor_multa = 5;
   }
   public void setaPermissao(boolean pp){
      //modifica a permissão do Usuário para retirada ou não de livros, pois,
      //diferente de funcionários, eles não têm acesso irrestrito aos livros
      permissao_retirada = pp;
   }
   public float setaMulta(int tempo){
      //multa aplicada em 10% cumulativos do valor original sobre a multa
      //original;
      float multa_aux = valor_multa;
      for(int clk = 0; clk < tempo; clk++){
         multa_aux *= 1.1;
      }
      return multa_aux;
   }
   public ArrayList<Brochura> retornaLista(){return livros_retirados;}
   public void adicionaLivro(Livro livro){livros_retirados.add(livro);}
      public void removeLivro(Livro livro){livros_retirados.remove(livro);}
   public void setaExcedente(int dias){dias_excedentes = dias;}
   public float retornaMulta(){return setaMulta(dias_excedentes);}

   public void retiraLivro(String nome){
      //retira um livro da lista de livros em posse do usuário
      //caso exista
      for(int clk = 0; clk < livros_retirados.size(); clk++){
         if(livros_retirados.get(clk).retornaNome().equals(nome)){
            livros_retirados.remove(clk);
            return;
         }
      }
   }
}

class Funcionario extends Individuo{
   public Funcionario(String nn, int rrgg){
      //terceiro argumento de super em true implica em funcionários terem acesso
      //irrestrito aos livros da biblioteca, sem necessidade de manutenção
      super(nn, rrgg, "funcionário", true);
   }
}

///////////////////////// SUBCLASSES DE BROCHURA ////////////////////////////////
class Livro extends Brochura{
   //livro deve ser retirado e devolvido
   public Livro(String nn, String au, int qtd){
      super(nn, au, qtd, true, true);
   }
   public Livro(Livro livro){
      super(livro.retornaNome(),
            livro.retornaAutor(),
            1, true, true);
      super.setaISBN(livro.retornaISBN());
   }
}

class Atlas extends Brochura{
   //atlas não pode ser retirado
   public Atlas(String nn, String au, int qtd){
      super(nn, au, qtd, false, false);
   }
}

class Revista extends Brochura{
   //revista pode ser retirada e não necessita de devolução
   public Revista(String nn, String au, int qtd){
      super(nn, au, qtd, true, false);
   }
}
