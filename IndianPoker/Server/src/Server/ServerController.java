package Server;

import com.google.gson.Gson;

public interface ServerController {
	Gson gson = new Gson();

	String runMethod(String string);
}
