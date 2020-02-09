package Data;
/*여러 dto를 쓰다 보니깐
 * 보다 효율적으로 쓰기 위한 메소드
 * */

public class SendJsonData {
	String	type;
	String	jsonObject;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

}
