package LolInvocador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ApiLol.json.org.json.JSONException;
import ApiLol.json.org.json.JSONObject;


		public class ApiInvocador {
			
			public static void main(String[] args) throws JSONException, Exception {
				
				Map<String, Integer> eloNivel = new HashMap<>();
				
					eloNivel.put("CHALLENGER" , 9);
					eloNivel.put("GRANDMASTER" , 8);
					eloNivel.put("MASTER" , 7);
					eloNivel.put("DIAMOND" , 6);
					eloNivel.put("PLATINUM" , 5);
					eloNivel.put("GOLD" , 4);
					eloNivel.put("SILVER" , 3);
					eloNivel.put("BRONZE" , 2);
					eloNivel.put("IRON" , 1);
					eloNivel.put("", 0);
					
					System.out.println(eloNivel);
					
					String eloJ = "";
					String eloJ2 = "";
				
				
				
				Scanner entrada = new Scanner(System.in);
				System.out.println("Username: ");
				String summonerName1 = entrada.nextLine();

				System.out.println("Username2: ");
				String summonerName2 = entrada.nextLine();
				
				//URL PRIMEIRO NICK
				String buscaNickSite1 = summonerName1.replaceAll(" " , "%20");
				URL urlChamadaNick1 = new URL("https://br1.api.riotgames.com/lol/summoner/v4/"
						+ "summoners/by-name/" + buscaNickSite1 + "?api_key=RGAPI-9833eec0-b056-45d3-8ee5-4c6bd8a1265e");
				
				
				BufferedReader in1 = new BufferedReader(new InputStreamReader(urlChamadaNick1.openStream()));
				String lolApiJson = in1.readLine();
				
				////pegando nick e level do invocador 1
				JSONObject lolApi = new JSONObject(lolApiJson);
				String name1 = lolApi.getString("name");
				int level1 = lolApi.getInt("summonerLevel");
				
				// ------------------------------------------------------------------------------------------------------------//
				
				//URL SEGUNDO NICK
				String buscaNickSite2 = summonerName2.replaceAll(" " , "%20");
				URL urlChamadaNick2 = new URL("https://br1.api.riotgames.com/lol/summoner/v4/"
						+ "summoners/by-name/" + buscaNickSite2 + "?api_key=RGAPI-9833eec0-b056-45d3-8ee5-4c6bd8a1265e");

				BufferedReader in2 = new BufferedReader(new InputStreamReader(urlChamadaNick2.openStream()));
				String lolApiJson2 = in2.readLine();
				

				//pegando nick e level do invocador 2
				JSONObject lolApi2 = new JSONObject(lolApiJson2);
				String name2 = lolApi2.getString("name");
				int level2 = lolApi2.getInt("summonerLevel");	
				
				
				
				// ----------------------------------------------------------------------------------------- //
				
				
				//pegando elo e rank do invocador 1
				String summonerElo1 = lolApi.getString("id");
				
				String buscaElo1 = summonerElo1.replaceAll("\\s", "");
				URL urlChamadaElo1 = new URL("https://br1.api.riotgames.com/lol/league/v4/entries"
						+ "/by-summoner/" + buscaElo1 + "?api_key=RGAPI-9833eec0-b056-45d3-8ee5-4c6bd8a1265e");
				
				BufferedReader eloIn1 = new BufferedReader(new InputStreamReader(urlChamadaElo1.openStream()));
				String eloJson = eloIn1.readLine().replace("[", "").replace("]", "");
					
				if(eloJson.isEmpty()) {
					System.out.println("\n Invocador: " + name1 + " is unranked!\n");
				} else {
				
					JSONObject eloApi = new JSONObject(eloJson);
					eloJ = eloApi.getString("tier");
					String rankJ = eloApi.getString("rank");
					int winJ = eloApi.getInt("wins");	
					
				
									
				//exibindo as informações 1
						System.out.println("Nick do invocador 1: " + name1);
						System.out.println("Level: " + level1);
						System.out.println("Tier: " + eloJ);
						System.out.println("Rank: " + rankJ);
						System.out.println("Wins: " + winJ);
						
				}
				
				
				
				// ----------------------------------------------------------------------- //
				
//				//pegando elo e rank do invocador 2
				String summonerElo2 = lolApi2.getString("id");
				
				String buscaElo2 = summonerElo2.replaceAll("\\s", "");
				URL urlChamadaElo2 = new URL("https://br1.api.riotgames.com/lol/league/v4/entries"
						+ "/by-summoner/" + buscaElo2 + "?api_key=RGAPI-9833eec0-b056-45d3-8ee5-4c6bd8a1265e");
				
				BufferedReader eloIn2 = new BufferedReader(new InputStreamReader(urlChamadaElo2.openStream()));
				String eloJson2 = eloIn2.readLine().replace("[", "").replace("]", "");
				
				
				if(eloJson2.isEmpty()) {
					System.out.println("\n Invocador: " + name2 + " is unranked!\n");
				} else {
				
					JSONObject eloApi2 = new JSONObject(eloJson2);
					eloJ2 = eloApi2.getString("tier");
					String rankJ2 = eloApi2.getString("rank");
					int winJ2 = eloApi2.getInt("wins");
													
					//exibindo as informações 2
					System.out.println("\n\nNick do invocador 2: " + name2);
					System.out.println("Level: " + level2);
					System.out.println("Tier: " + eloJ2);
					System.out.println("Rank: " + rankJ2);
					System.out.println("Wins: "  + winJ2);
				} 
					
				// Comparando elo dos invocadores
				int eloInvoncador1 = eloNivel.get(eloJ.toUpperCase());
				int eloInvoncador2 = eloNivel.get(eloJ2.toUpperCase());
				
				if (eloInvoncador1 > eloInvoncador2 ) {
					System.out.println("\nInvocador 1 é Superior: " + eloJ);
					System.out.println("Invocador 2 é Inferior: " + eloJ2);
					
				} else if (eloInvoncador1 < eloInvoncador2){
					System.out.println("\nInvocador 2 é Superior: " + eloJ2);
					System.out.println("Invocador 1 é Inferior: " + eloJ);
					
				} else if (eloInvoncador1 <= 0|| eloInvoncador2 <= 0){
					System.out.println("\nAmbos são Unranked!");
					
				} else {
					System.out.println("Ambos sao do mesmo nivel!");
				}
			
				entrada.close();
				
		}
			

		


	}


