package com.hhs.xgn.soundguess.pkmod;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.hhs.xgn.soundguess.game.Mod;
import com.hhs.xgn.soundguess.game.SoundGuess;

@com.hhs.xiaomao.modloader.Mod(modid = "pkmod", name = "Pokemon", version = "5")
public class PokemonMod extends Mod{

	@Override
	public int getBuildVersion() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	@Override
	public String getModName() {
		// TODO Auto-generated method stub
		return "Pokemon";
	}

	
	@Override
	public void init() {
		
		// TODO Auto-generated method stub
		System.out.println("Pokemon Guess Mod For version 5 By XGN!");
	}

	@Override
	public URL getMusic(SoundGuess self,int id) {
		// TODO Auto-generated method stub
		try{
			return new URL("https://raw.githubusercontent.com/XiaoGeNintendo/public-resource-hut/master/pokemon/cries/mp3/"+id+".ogg.mp3");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public URL getPicture(SoundGuess self,int id) {
		try{
			return new URL("https://veekun.com/dex/media/pokemon/main-sprites/ultra-sun-ultra-moon/"+id+".png");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isIdOk(SoundGuess self,int id) {
		// Always Ok
		return true;
	}

	@Override
	/**
	 * The picture is 1-807 <br/>
	 * The voice is 1-721
	 */
	public int getLimit(SoundGuess self) {
		return 721; //The maximum pokemon count in Ultra sun and moon
	}

	@Override
	public Map<String, URL> onAcquired(SoundGuess self,int id) {
		try{
			Map<String,URL> mp=new HashMap<>();
			
			mp.put("POffical Artwork by Sugimori", new URL("https://veekun.com/dex/media/pokemon/sugimori/"+id+".png"));
			
			mp.put("ODreamwork Artwork in SVG format", new URL("https://veekun.com/dex/media/pokemon/dream-world/"+id+".svg"));
			
			String sid=""+id;
			while(sid.length()<3){
				sid="0"+sid;
			}
			
			if(id<=649){
				mp.put("PGif Front", new URL("http://media.52poke.com/assets/sprite/b2w2/front/"+sid+".gif"));
				mp.put("PGif Back", new URL("http://media.52poke.com/assets/sprite/gen5/"+sid+"b.gif"));
				mp.put("PGif Front Shiny", new URL("http://media.52poke.com/assets/sprite/b2w2/front_shiny/"+sid+".gif"));
				mp.put("PGif Back Shiny", new URL("http://media.52poke.com/assets/sprite/gen5/"+sid+"sb.gif"));
			}
			
			
			return mp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String isCorrect(SoundGuess self,int id, String name) {
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
						return "Cok, acceptable answer from "+Arrays.toString(tokens);
					}else{
						s.close();
						return "WExpected:"+Arrays.toString(tokens)+" But found "+name;
					}
				}
			}
			
			System.out.println("Failed to find such pattern");
			
			s.close();
			return "UFailed to find such Pokemon with Id";
		}catch(Exception e){
			e.printStackTrace();
			return "UAn Exception occurred:"+e;
		}
	}
	
	@Override
	public void openCustomMenu(SoundGuess self) {
		JFrame jf=new JFrame("Pokemon Credits");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLayout(new GridLayout(5, 1));
		
		JLabel jl=new JLabel("Thx for installing Pokemon Mod!");
		JLabel jl2=new JLabel("This mod is by XGN from HHS 2019");
		JLabel jl3=new JLabel("A Pokemon and Development Lover");
		JLabel jl4=new JLabel("Also the creator of the whole game");
		JButton jb=new JButton("Get a special prize!");
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					HashMap<String,URL> hm=new HashMap<String,URL>();
					hm.put("PAll Starter Pokemon", new URL("https://github.com/XiaoGeNintendo/public-resource-hut/raw/master/pokemon/InitalpokemonList.png"));
					
					self.save.acquired.put("Pokemon:Addition", hm);
					
					self.saveData();
					
					JOptionPane.showMessageDialog(null, "Thanks for playing!\nThe gift was sent to your inventory!\nCheck Pokemon:Addition for it!");
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Ahh! A Zzzyt comes and absorbs the prize! :(\nCome and try another day!");
				}
			}
		});
		jf.add(jl);
		jf.add(jl2);
		jf.add(jl3);
		jf.add(jl4);
		jf.add(jb);
		jf.pack();
		jf.setVisible(true);
	}
}
