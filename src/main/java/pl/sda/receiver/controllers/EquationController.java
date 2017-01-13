package pl.sda.receiver.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sda.receiver.messages.Equation;
import pl.sda.receiver.messages.EquationResult;
import pl.sda.receiver.messages.Solve;

@Controller
@RequestMapping("/equations")
public class EquationController {

	private static final String SUCCESS_MESSAGE = "OK";
	private static final String ERROR_MESSAGE = "ERROR";

	private final AtomicLong counter = new AtomicLong();

	private final Map<String, Integer> results = new HashMap<>();

	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public @ResponseBody Equation getEquation() {
		String id = String.valueOf(counter.getAndIncrement());
		Random random = new Random();
		int left = random.nextInt() - 32768;
		int right = random.nextInt() - 32768;
		int x = right + (-1 * left);
		results.put(id, x);

		Equation result = new Equation();
		result.setId(id);
		String sign = left < 0 ? "" : "+";
		String linearEquation = "x" + sign + left + "=" + right;
		result.setProblem(linearEquation);
		System.out.println("Równanie " + id + "; " + linearEquation + "; x=" + x);
		return result;
	}

	@RequestMapping(path = "/solve", method = RequestMethod.POST)
	public @ResponseBody Solve getResult(@RequestBody EquationResult equationResult) {
		Solve response = new Solve();
		response.setAnswer(ERROR_MESSAGE);
		if (equationResult != null && equationResult.getId() != null && equationResult.getResult() != null) {
			String id = equationResult.getId();
			response.setId(id);
			Integer result = results.get(id);
			if (result != null && result.equals(equationResult.getResult())) {
				response.setAnswer(SUCCESS_MESSAGE);
				response.setId(id);
			}
		}
		return response;
	}
}
