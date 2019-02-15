package io.github.NadhifRadityo.CircleFellowship.Core.Object.Canvas;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComponent;

public class CanvasPanel extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2291182424768508432L;
	protected final Map<Sprite, Integer> sprites = new HashMap<>();
	protected int maxPriority = 0;
	protected int minPriority = 0;

	public Map<Sprite, Integer> getSprites() {
		return sprites;
	}
	public void addSprite(Sprite sprite, int priority) {
		sprites.put(sprite, priority);
		reinitPriority();
	}
	public void addSprite(Sprite sprite) {
		addSprite(sprite, 0);
	}
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
		reinitPriority();
	}
	
	public void reinitPriority() {
		Integer[] intgs = sprites.values().toArray(new Integer[sprites.size()]);
		for(int intg : intgs) {
			maxPriority = Math.max(maxPriority, intg);
			minPriority = Math.min(minPriority, intg);
		}
	}
	protected Map<Integer, List<Sprite>> swapSpritePriority() {
		Map<Integer, List<Sprite>> ret = new HashMap<>();
		for(Entry<Sprite, Integer> pair : sprites.entrySet()) {
			if(ret.get(pair.getValue()) == null) ret.put(pair.getValue(), new ArrayList<Sprite>());
			ret.get(pair.getValue()).add(pair.getKey());
        }
		return ret;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Map<Integer, List<Sprite>> priority = swapSpritePriority();
		for(int i = minPriority; i <= maxPriority; i++) {
			if(priority.get(i) == null) continue;
			priority.get(i).forEach(e -> e.draw(g));
        }
	}
}
