package com.hhs.xgn.soundguess.pkmod;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.hhs.xgn.soundguess.game.Mod;

@com.hhs.xiaomao.modloader.Mod(modid = "", name = "", version = "")
public class PokemonMod extends Mod{

	@Override
	public String getModName() {
		// TODO Auto-generated method stub
		return "Pokemon";
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("Pokemon Guess Mod By XGN!");
	}

	@Override
	public URL getMusic(int id) {
		// TODO Auto-generated method stub
		try{
			return new URL("https://raw.githubusercontent.com/XiaoGeNintendo/public-resource-hut/master/pokemon/cries/mp3/"+id+".ogg.mp3");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public URL getPicture(int id) {
		try{
			return new URL("https://veekun.com/dex/media/pokemon/main-sprites/ultra-sun-ultra-moon/"+id+".png");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isIdOk(int id) {
		// Always Ok
		return true;
	}

	@Override
	/**
	 * The picture is 1-807 <br/>
	 * The voice is 1-721
	 */
	public int getLimit() {
		return 721; //The maximum pokemon count in Ultra sun and moon
	}

	@Override
	public Map<String, URL> onAcquired(int id) {
		try{
			Map<String,URL> mp=new HashMap<>();
			
			mp.put("POffical Artwork by Sugimori", new URL("https://veekun.com/dex/media/pokemon/sugimori/"+id+".png"));
			
			mp.put("PDreamwork Artwork", new URL("https://veekun.com/dex/media/pokemon/dream-world/"+id+".svg"));
			
			if(id<=649){
				mp.put("PGif Front", new URL("http://media.52poke.com/assets/sprite/b2w2/front/"+id+".gif"));
				mp.put("PGif Back", new URL("http://media.52poke.com/assets/sprite/gen5/"+id+"b.gif"));
				mp.put("PGif Front Shiny", new URL("http://media.52poke.com/assets/sprite/b2w2/front_shiny/"+id+".gif"));
				mp.put("PGif Back Shiny", new URL("http://media.52poke.com/assets/sprite/gen5/"+id+"sb.gif"));
			}
			
			
			return mp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isCorrect(int id, String name) {
		try{
			String url="https://raw.githubusercontent.com/XiaoGeNintendo/public-resource-hut/master/pokemon/list_new.txt";
			Scanner s=new Scanner(new URL(url).openStream(),"utf-8");
			String sId=""+id;
			while(sId.length()<3){
				sId="0"+sId;
			}
			
			System.out.println("Pattern:#"+sId);
			
			while(s.hasNextLine()){
				String line=s.nextLine();
				String[] tokens=line.split("\\t");
				
//				System.out.println(Arrays.toString(tokens));
				if(tokens[0].equals("#"+sId)){
					if(tokens[1].equalsIgnoreCase(name)||
							tokens[2].equalsIgnoreCase(name) ||
							tokens[3].equalsIgnoreCase(name)){
						System.out.println("Correct Answer");
						
						s.close();
						return true;
					}else{
						System.out.println("Expected:"+Arrays.toString(tokens)+" But found "+name);
						s.close();
						return false;
					}
				}
			}
			
			System.out.println("Failed to find such pattern");
			
			s.close();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
