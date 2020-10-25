package cena;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT; //primitivas 3D

import java.awt.Color;
import java.awt.Font;

/**
 * @author Siabreu
 */
public class Cena implements GLEventListener {
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    public float anguloX = 0, anguloY = 0, anguloZ = 0;
    public boolean zbuffer = true;
    private TextRenderer textRenderer;
    //Preenchimento
    public int mode;
    public int tonalizacao = GL2.GL_SMOOTH;
    public float luzR = 0.2f, luzG = 0.2f, luzB = 0.2f;
    public float luzDifusaX = 0.7f, luzDifusaY = 0.7f, luzDifusaZ = 0.7f;
    public float luzEspecularX = 1f, luzEspecularY = 1f, luzEspecularZ = 1f;
    public float corR = 1f, corG = 1f, corB = 1f;
    public float luzPosX = 0f, luzPosY = 0f, luzPosZ = 1f;

    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena        
        GL2 gl = drawable.getGL().getGL2();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;

        textRenderer = new TextRenderer(new Font(null, Font.PLAIN, 15));
        //Habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        //objeto para desenho 3D
        GLUT glut = new GLUT();

        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 0, 0, 1);
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); //ler a matriz identidade

        iluminacao(gl);
        ligaLuz(gl);

        this.DrawCircle(gl, glut);
        this.DrawTeapot(gl, glut);
        this.DrawTorus(gl, glut);
        this.DrawCube(gl, glut);
        this.DrawCone(gl, glut);
        this.DrawCylinder(gl, glut);

        desenhaTexto(gl, 0, 570, Color.white, "AnguloX: " + anguloX);
        desenhaTexto(gl, 200, 570, Color.white, "AnguloY: " + anguloY);

        desenhaTexto(gl, 400, 570, Color.white, "Tonalizacao: " + (tonalizacao == GL2.GL_SMOOTH ? "smooth" : "flat"));

        desenhaTexto(gl, 0, 200, Color.white, "Luz vermelha: " + luzR);
        desenhaTexto(gl, 0, 180, Color.white, "Luz verde: " + luzG);
        desenhaTexto(gl, 0, 160, Color.white, "Luz azul: " + luzB);

        desenhaTexto(gl, 200, 200, Color.white, "Cor vermelha: " + corR);
        desenhaTexto(gl, 200, 180, Color.white, "Cor verde: " + corG);
        desenhaTexto(gl, 200, 160, Color.white, "Cor azul: " + corB);

        desenhaTexto(gl, 0, 120, Color.white, "Luz pos X: " + luzPosX);
        desenhaTexto(gl, 0, 100, Color.white, "Luz pos Y: " + luzPosY);
        desenhaTexto(gl, 0, 80, Color.white, "Luz pos Z: " + luzPosZ);

        desenhaTexto(gl, 0, 40, Color.white, "Luz especular X: " + luzEspecularX);
        desenhaTexto(gl, 0, 20, Color.white, "Luz especular Y: " + luzEspecularY);
        desenhaTexto(gl, 0, 0, Color.white, "Luz especular Z: " + luzEspecularZ);

        desenhaTexto(gl, 200, 120, Color.white, "Luz difusa X: " + luzDifusaX);
        desenhaTexto(gl, 200, 100, Color.white, "Luz difusa Y: " + luzDifusaY);
        desenhaTexto(gl, 200, 80, Color.white, "Luz difusa Z: " + luzDifusaZ);

        desenhaTexto(gl, 390, 0, Color.white, "Guilherme Carvalhal (21002514)");

        gl.glFlush();
    }

    private void DrawCircle(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(corR, corG, corB); //cor do objeto
        gl.glRotatef(anguloX, 1, 0, 0);
        gl.glRotatef(anguloY, 0, 1, 0);
        gl.glRotatef(anguloZ, 0, 0, 1);
        glut.glutSolidSphere(15, 360, 15);
        gl.glPopMatrix();
    }

    private void DrawTeapot(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(corR, corG, corB); //cor do objeto
        gl.glTranslated(60, 0, 0);
        gl.glRotatef(anguloX, 1, 0, 0);
        gl.glRotatef(anguloY, 0, 1, 0);
        gl.glRotatef(anguloZ, 0, 0, 1);
        glut.glutSolidTeapot(15);
        gl.glPopMatrix();
    }

    private void DrawTorus(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(corR, corG, corB); //cor do objeto
        gl.glTranslated(-60, 0, 0);
        gl.glRotatef(anguloX, 1, 0, 0);
        gl.glRotatef(anguloY, 0, 1, 0);
        gl.glRotatef(anguloZ, 0, 0, 1);
        glut.glutSolidTorus(5, 10, 15, 360);
        gl.glPopMatrix();
    }

    private void DrawCube(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(corR, corG, corB); //cor do objeto
        gl.glTranslated(-60, 60, 0);
        gl.glRotatef(anguloX, 1, 0, 0);
        gl.glRotatef(anguloY, 0, 1, 0);
        gl.glRotatef(anguloZ, 0, 0, 1);
        glut.glutSolidCube(25);
        gl.glPopMatrix();
    }

    private void DrawCone(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(corR, corG, corB); //cor do objeto
        gl.glTranslated(0, 60, 0);
        gl.glRotatef(anguloX, 1, 0, 0);
        gl.glRotatef(anguloY, 0, 1, 0);
        gl.glRotatef(anguloZ, 0, 0, 1);
        glut.glutSolidCone(15, 20, 15, 15);
        gl.glPopMatrix();
    }

    private void DrawCylinder(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(corR, corG, corB); //cor do objeto
        gl.glTranslated(60, 60, 0);
        gl.glRotatef(anguloX, 1, 0, 0);
        gl.glRotatef(anguloY, 0, 1, 0);
        gl.glRotatef(anguloZ, 0, 0, 1);
        glut.glutSolidCylinder(15, 20, 15, 15);
        gl.glPopMatrix();
    }


    public void desenhaTexto(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase) {
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        //Retorna a largura e altura da janela
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer.setColor(cor);
        textRenderer.draw(frase, xPosicao, yPosicao);
        textRenderer.endRendering();
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);
    }

    public void iluminacao(GL2 gl) {
        float luzAmbiente[] = new float[4];
        luzAmbiente[0] = luzR;
        luzAmbiente[1] = luzG;
        luzAmbiente[2] = luzB;
        luzAmbiente[3] = 1.0f;

        float luzDifusa[] = {luzDifusaX, luzDifusaY, luzDifusaZ, 1.0f};
        float luzEspecular[] = {luzEspecularX, luzEspecularY, luzEspecularZ, 1.0f};
        float posicaoLuz[] = {luzPosX, luzPosY, luzPosZ, 0.0f};

        // capacidade de brilho do material        
        int especMaterial = 60;

        // define a concentra��o do brilho
        gl.glMateriali(GL2.GL_FRONT, GL2.GL_SHININESS, especMaterial);

        // ativa o uso da luz ambiente
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, luzAmbiente, 0);

        // define os par�metros de luz de n�mero 0 (zero)
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, luzAmbiente, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, luzDifusa, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, luzEspecular, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicaoLuz, 0);
    }

    public void ligaLuz(GL2 gl) {
        // habilita a defini��o da cor do material a partir da cor corrente
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        // habilita o uso da ilumina��o na cena
        gl.glEnable(GL2.GL_LIGHTING);
        // habilita a luz de n�mero 0
        gl.glEnable(GL2.GL_LIGHT0);

        /*
         * Especifica o Modelo de tonaliza��o a ser utilizado GL_FLAT -> modelo de
         * tonaliza��o flat GL_SMOOTH -> modelo de tonaliza��o GOURAUD (default)
         */
        gl.glShadeModel(tonalizacao);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();

        //evita a divisao por zero
        if (height == 0) height = 1;
        //calcula a proporcao da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;

        //seta o viewport para abranger a janela inteira
        //gl.glViewport(0, 0, width, height);

        //ativa a matriz de projecao
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //ler a matriz identidade

        //Projecaoo ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
//        if(width >= height)            
//            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
//        else        
//            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);

        //projecao ortogonal sem a correcao do aspecto
        gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //ler a matriz identidade
    }

    public void reset() {
        anguloX = 0;
        anguloY = 0;
        anguloZ = 0;

        tonalizacao = GL2.GL_SMOOTH;

        luzR = 0.2f;
        luzG = 0.2f;
        luzB = 0.2f;
        luzDifusaX = 0.7f;
        luzDifusaY = 0.7f;
        luzDifusaZ = 0.7f;
        luzEspecularX = 1f;
        luzEspecularY = 1f;
        luzEspecularZ = 1f;
        corR = 1f;
        corG = 1f;
        corB = 1f;
        luzPosX = 0f;
        luzPosY = 0f;
        luzPosZ = 1f;
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}
