package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	public RedesController(){
		super();
	}
	
	public void ip(String os){
		if(os.contains("Windows")){
			String process="ipconfig";
			try {
				Process p = Runtime.getRuntime().exec(process);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null){
					if(linha.contains("Adaptador Ethernet")){
						System.out.println(linha.substring(linha.indexOf("Adaptador") , linha.indexOf(":")));
					}
					else if(linha.contains("Wi-Fi")){
						System.out.println(linha.substring(linha.indexOf("Adaptador") , linha.indexOf(":")));
					}
					else if(linha.contains("IPv4")){
						System.out.println(linha.substring(linha.indexOf("IPv4")));
					}
					
					linha=buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch(IOException e){
				e.printStackTrace();		
			}
		}
		else if(os.contains("Linux")){
			String process="ifconfig";
			try {
				Process p = Runtime.getRuntime().exec(process);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null){
					if(linha.contains("inet ")){
						System.out.println(linha.substring(linha.indexOf("inet ") , linha.indexOf("netmask")));
					}				
					linha=buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch(IOException e){
				e.printStackTrace();		
			}
		}
		else{
			System.out.println("Sistema Operacional não suportado.");
		}
	}
	public void ping(String os){
		if(os.contains("Windows")){
			String process="PING -4 -n 10 www.google.com.br";
			try {
				double media=0;
				String g;
				Process p = Runtime.getRuntime().exec(process);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null){
					if(linha.contains("Resposta")){
						System.out.println(linha.substring(linha.indexOf("tempo=") , linha.indexOf("ms"))+"ms");
						g=(linha.substring(linha.indexOf("tempo=") , linha.indexOf("ms")));
						g = g.replaceAll("\\D+","");
						media=media+Integer.parseInt(g);
					}
					linha=buffer.readLine();
				}
				media=media/10;
				System.out.println("Média=" + media+ "ms");
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch(IOException e){
				e.printStackTrace();		
			}
		}
		else if(os.contains("Linux")){
			String process="ping -4 -c 10 www.google.com.br";
			try {
				double media=0;
				String g="";
				Process p = Runtime.getRuntime().exec(process);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null){
					if(linha.contains("bytes from")){
						System.out.println(linha.substring(linha.indexOf("time=") , linha.indexOf(" ms"))+"ms");
						g=(linha.substring(linha.indexOf("time=") , linha.indexOf(" ms")));
						g = g.replaceAll("\\D+","");
						media=media+Double.parseDouble(g);
					}
					linha=buffer.readLine();
				}
				media=media/100;
				System.out.println("Média=" + media+ "ms");
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch(IOException e){
				e.printStackTrace();		
			}
		}
	}
}