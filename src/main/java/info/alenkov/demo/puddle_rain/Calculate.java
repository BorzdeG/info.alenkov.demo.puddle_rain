package info.alenkov.demo.puddle_rain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculate {
	private transient static final Logger LOG = LoggerFactory.getLogger(Calculate.class.getName());

	public static void main(String[] args) {
		Integer[] data = {2, 5, 1, 2, 3, 4, 7, 7, 6};
		LOG.info("result: {}", calculateVolume(data));
	}

	public static int calculateVolume(Integer[] data) {
		int volume = 0;
		int t_volume = 0;

		int h_l = data[0]; // Высота "стартовой" левой границы
		int h_p = h_l; // Предыдущая высота
		int h_r = h_l; // Высота правой границы
		int s = 0; // Сколько "прощли" по X от левой до правой границы
		boolean b = false; // Признак наличия "впадины"

		for (int i = 1; i < data.length; i++) {
			h_r = data[i];
			LOG.debug("left: {}; prev: {}; right: {}; b: {}", h_l, h_p, h_r, b);

			if (h_r != h_p) {
				LOG.debug("Дно изменяет высоту");
				s++;
				if (h_r < h_l) {
					LOG.debug("Правая граница всё ещё под водой");
					t_volume += h_l - h_r;
					b = h_r > h_p || b;
				} else if (h_r > h_p) {
					LOG.debug("Правая граница над водой");
					volume += t_volume;
					t_volume = 0;
					h_l = h_r;
					s = 0;
					b = false;
				}
			} else {
				LOG.debug("Плоское дно");
				t_volume += h_l - h_r;
			}
			h_p = h_r;

			LOG.debug("volume: {}; t_volume: {}", volume, t_volume);
		}

		if (h_r < h_l) {
			LOG.debug("Убираем 'излишки' воды");
			LOG.debug("left: {}; prev: {}; right: {}; b: {}", h_l, h_p, h_r, b);
			if (b) {
				final int d = (h_l - h_p) * s;
				LOG.debug("излишки: {}", d);
				volume += t_volume - d;
			}
		}

		return volume;
	}
}
