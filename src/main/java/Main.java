import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;

public class Main {
    public static void main(String[] args) throws Exception {
        long window = Window.windowCreate();
        float yMovement = 0.009f;
        float xMovement = 0.006f; //this was carefully crafted so it resembles the dvd screensaver
        //youtube.com/watch?v=Kxms-OtUXS0
        Side collidingSide;

        Square dvd = new Square(0, 0, 0.3f);
        dvd.init();

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL33.glClearColor(0f, 0f, 0f, 1f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            dvd.render();
            dvd.update(xMovement, yMovement);
            collidingSide = dvd.colides();
            if (collidingSide != null){
                switch (collidingSide){
                    case TOP, BOTTOM -> yMovement *= -1f;
                    case LEFT, RIGHT -> xMovement *= -1f;
                    default -> {}
                }
            }
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
        GLFW.glfwTerminate();
    }
}
