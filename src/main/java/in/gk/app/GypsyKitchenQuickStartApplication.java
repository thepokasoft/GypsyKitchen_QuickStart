package in.gk.app;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.gk.app.api.DailyReport;
import in.gk.app.api.SendEmail;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SpringBootApplication
public class GypsyKitchenQuickStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(GypsyKitchenQuickStartApplication.class, args);

		final ScheduledExecutorService keppAliveScheduler = Executors.newScheduledThreadPool(1);
		keppAliveScheduler.scheduleAtFixedRate(new KeepAliveRunnable(), 0, 29, TimeUnit.MINUTES);
		final ScheduledExecutorService dailyReportsScheduler = Executors.newScheduledThreadPool(1);
		dailyReportsScheduler.scheduleAtFixedRate(new dailyReportsRunnable(), 0, 1, TimeUnit.DAYS);
	}

}

class KeepAliveRunnable implements Runnable {

	public void run() {

		try {
			new OkHttpClient().newCall(new Request.Builder().url("https://gypsykitchenqs.herokuapp.com/").build())
					.execute();
			System.out.println("::::|||| Executing Thread to Keep System Alive ||||::::");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class dailyReportsRunnable implements Runnable {

	public void run() {
		//new DailyReport().getTotalOrderDetails();
	}
}
