package input;
import cena.Cena;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;

import java.util.Scanner;

/**
 *
 * @author Siabreu
 */
public class KeyBoard implements KeyListener{
    private Cena cena;
    
    public KeyBoard(Cena cena){
        this.cena = cena;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {        

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        if ( e.getKeyCode() == 149) {
            cena.anguloY -= 10f;
        }
        else if(e.getKeyCode() == 151)
        {
            cena.anguloY += 10f;
        }

        if ( e.getKeyCode() == 150) {
            cena.anguloX -= 10f;
        }
        else if(e.getKeyCode() == 152)
        {
            cena.anguloX += 10f;
        }

        if(e.getKeyChar() == 't') {
            cena.tonalizacao = cena.tonalizacao == GL2.GL_SMOOTH ? GL2.GL_FLAT : GL2.GL_SMOOTH;
        }

        if(e.getKeyChar() == 'l')
        {
            System.out.println("Entre os valores para luz vermelha, verde e azul: ");
            cena.luzR = this.readInput();
            cena.luzG = this.readInput();
            cena.luzB = this.readInput();
        }

        if(e.getKeyChar() == 'c')
        {
            System.out.println("Entre os valores para cor vermelha, verde e azul: ");
            cena.corR = this.readInput();
            cena.corG = this.readInput();
            cena.corB = this.readInput();
        }

        if(e.getKeyChar() == 'e')
        {
            System.out.println("Entre os valores para luz especular: ");
            cena.luzEspecularX = this.readInput();
            cena.luzEspecularY = this.readInput();
            cena.luzEspecularZ = this.readInput();
        }

        if(e.getKeyChar() == 'p')
        {
            System.out.println("Entre os valores para a posicao da luz: ");
            cena.luzPosX = this.readInput();
            cena.luzPosY = this.readInput();
            cena.luzPosZ = this.readInput();
        }

        if(e.getKeyChar() == 'd')
        {
            System.out.println("Entre os valores para luz difusa: ");
            cena.luzDifusaX = this.readInput();
            cena.luzDifusaY = this.readInput();
            cena.luzDifusaZ = this.readInput();
        }

        if(e.getKeyChar() == 'r')
        {
            cena.reset();
        }

    }

    private float readInput()
    {
        Scanner in = new Scanner(System.in);

        return in.nextFloat();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}
