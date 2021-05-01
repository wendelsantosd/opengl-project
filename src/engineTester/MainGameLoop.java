package engineTester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Entity;
import entities.Light;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		//RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
		//TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("image")));

		Entity entity = new Entity(staticModel, new Vector3f(0,0,-25),0,0,0,1);
		Light light = new Light(new Vector3f(0,0,-20), new Vector3f(1,1,1));
		
		
		while(!Display.isCloseRequested()){
			if(Keyboard.isKeyDown(Keyboard.KEY_X)){
				entity.increaseRotation(1, 0, 0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_Y)){
				entity.increaseRotation(0, 1, 0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
				entity.increaseRotation(0, 0, 1);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_K)){
				entity.setScale(-0.005f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_L)){
				entity.setScale(0.005f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_E)){
				entity.increasePosition(0, 0, -0.1f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
				entity.increasePosition(0, 0, 0.1f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				entity.increasePosition(0, 0.1f,0 );
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				entity.increasePosition(0, -0.1f, 0);
				}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				entity.increasePosition(-0.1f, 0, 0);
				}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			
				entity.increasePosition(0.1f, 0, 0);
			}
			//entity.increaseRotation(1, 1, 0);
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			renderer.render(entity,shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}