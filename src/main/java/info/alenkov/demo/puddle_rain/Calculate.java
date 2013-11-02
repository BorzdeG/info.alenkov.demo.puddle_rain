package info.alenkov.demo.puddle_rain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculate {
	private transient static final Logger LOG = LoggerFactory.getLogger(Calculate.class.getName());

	private static int v; // Объём воды
	private static int v_u; // Объём воды над "ватерлинией"
	private static int v_d; // Объём воды под "ватерлинией"

	private static int s_l; // Сколько "прошагали" от левой границы
	private static int s_pl; // Сколько "прошагали" от максимальной высоты "под водой"

	private static int h_l; // Высота "стартовой" левой границы
	private static int h_p; // Предыдущая высота
	private static int h_pl; // Максимальная высота "под водой"
	private static int h_c; // Высота на текущем шаге


	private static void breathedAir() {
		LOG.debug("Вдохнули воздуха");

		// Сбрасываем счётчики
		v += v_u + v_d;
		v_u = 0;
		v_d = 0;
		s_l = -1;
		s_pl = -1;
		// И начинаем движение с новой точки
		h_l = h_c;
		h_pl = h_c;
	}

	private static void movingUnderWaterUp() {
		LOG.debug("Всё выше и выше и выше");

		int h_d = (h_c > h_pl) ? ((h_c - h_pl) * s_l) : ((h_pl == h_l) ? (h_c - h_p) : 0);
		LOG.debug("h_d: {}", h_d);

		if (h_c >= h_pl) {
			h_pl = h_c;
			s_pl = -1;
		}
		v_u += (h_l - h_pl) - h_d;
		v_d += Math.abs(h_c - h_pl) + h_d;
	}

	private static void movingUnderWaterDown() {
		LOG.debug("В глубины пучины");

		if (h_pl == h_l) {
			v_u += h_l - h_c;
			if (s_l == 0) {
				h_pl = h_c;
			}
		} else {

			int h_d = Math.abs(h_c - h_pl);
			v_u += h_l - h_pl;
			v_d += h_d;
		}
	}

	private static void movingUnderWater() {
		LOG.debug("Двигаемся под водой");

		if (h_c > h_p) {
			movingUnderWaterUp();
		} else if (h_c < h_l) {
			movingUnderWaterDown();
		}
	}

	private static void alignHeightWater() {
		LOG.debug("Сливаем излишки");

		int d = 0;

		if (h_pl > h_c && s_l == s_pl) {
			v_u = 0;
			v_d = 0;
		} else if (h_pl > h_c) {
			LOG.debug("Шлюз 0");
			v_u -= (h_l - h_pl) * s_l;

			LOG.debug("Шлюз 1");
			v_d -= (h_pl - h_c) * s_pl;
		} else {
			v_u -= (h_l - h_c) * s_l;
		}
		LOG.debug("d: {}", d);

		v += v_u + v_d;
		v_u = 0;
		v_d = 0;

		h_l = h_c;
		h_pl = h_c;
		s_l = -1;
		s_pl = -1;
	}

	public static int calculateVolume(Integer[] data) {

		h_l = data[0];
		h_p = data[0];
		h_pl = data[0];
		h_c = data[0];
		int h_r = data[data.length - 1]; // Высота правой границы

		v_u = 0;
		v_d = 0;
		v = 0;

		s_l = 0;
		s_pl = 0;

		LOG.debug("--- 0 ---");
		LOG.debug("h_l: {}; h_c: {}; h_p: {}; h_pl: {}; h_r: {}", h_l, h_c, h_p, h_pl, h_r);

		for (int i = 1; i < data.length; i++) {
			h_c = data[i];
			LOG.debug("--- {} ---", i);
			LOG.debug("s_l: {}; s_pl: {}", s_l, s_pl);
			LOG.debug("h_l: {}; h_c: {}; h_p: {}; h_pl: {}; h_r: {}", h_l, h_c, h_p, h_pl, h_r);

			if (h_c >= h_l) {
				breathedAir();
			} else if (h_c > h_r && s_pl > 0) {
				alignHeightWater();
			} else if (h_c < h_l) {
				movingUnderWater();
			} else {
				LOG.error("Неучтённая ситуация");
			}

			h_p = h_c;
			s_l++;
			s_pl++;

			LOG.debug("v_u: {}; v_d: {}; v: {}", v_u, v_d, v);
		}
		if (h_l > h_r) {
			alignHeightWater();
		}

		LOG.debug("v_u: {}; v_d: {}; v: {}", v_u, v_d, v);

		return v;
	}
}
