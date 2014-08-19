package testes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TesteGateway {
	static String SMSApi = "https://site2sms.p.mashape.com/index.php?uid=9095393259&pwd=554182&phone=55119513138693&msg=hai";
	static String head = "aVi7utR6ZUkGLFkGRwXxd4wLsXz7c1QQ";

	public static void main(String[] args) {
		try {
			String url = SMSApi;
			String smsApiResponse = sendMySMS(url);
			System.out.println(smsApiResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String sendMySMS(String url) {
		StringBuilder output = new StringBuilder();
		try {
			URL hp = new URL(url);
			System.out.println(url);
			URLConnection hpCon = hp.openConnection();
			hpCon.setRequestProperty("X-Mashape-Authorization", head);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					hpCon.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				output.append(inputLine);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}