import java.util.*;

public class App {
    public static int maxprofit = 0;
    public static int maxProcess_time = 0;

    public static class Job {
        String name;
        int process_time;
        int profit;

        public Job(String name, int process_time, int profit) {
            this.name = name;
            this.process_time = process_time;
            this.profit = profit;
        }
    }

    public static class Result {
        String name;
        int process_time;
        int profit;

        public Result(String name, int process_time, int profit) {
            this.name = name;
            this.process_time = process_time;
            this.profit = profit;
        }

    }

    public static List<Result> printJobScheduling(Job[] jobs) {
        // Mengurutkan profit dari yang terbesar ke terkecil
        List<Result> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o2.profit - o1.profit;
            }
        });
        // Ambil profit terbesar dan waktu pengerjaan
        list.add(new Result(jobs[0].name, jobs[0].process_time, jobs[0].profit));
        // Set<Integer> set = new HashSet<>();
        // set.add(jobs[0].process_time);
        // set.add(jobs[0].profit);

        list1.add(jobs[0].process_time);
        list1.add(jobs[0].profit);
        maxprofit += jobs[0].profit;
        maxProcess_time += jobs[0].process_time;
        for (int i = 1; i < jobs.length; i++) {
            if (list1.add(jobs[i].process_time)) {
                list.add(new Result(jobs[i].name, jobs[i].process_time, jobs[i].profit));
                maxprofit += jobs[i].profit;
            }
            if (list1.add(jobs[i].profit)) {
                maxProcess_time += jobs[i].process_time;
            }
        }

        Collections.sort(list, new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o1.process_time - o2.process_time;
            }
        });
        return list;
    }

    public static void display(List<Result> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name + " ");
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Jumlah Pekerjaan : ");
        int jumlahKerja = input.nextInt();

        // inisialisasi jumlah pekerjaan
        Job[] jobs = new Job[jumlahKerja];

        for (int i = 0; i < jobs.length; i++) {
            System.out.print("\n");
            System.out.print("ID Pekerjaan " + (i + 1) + ": ");
            String Name = input.next();

            System.out.print("Waktu Pengerjaan " + (i + 1) + ": ");
            int Process_Time = input.nextInt();

            System.out.print("Profit " + (i + 1) + ": ");
            int Profit = input.nextInt();

            jobs[i] = new Job(Name, Process_Time, Profit);
        }
        input.close();
        System.out.print("\n");
        System.out.println("Urutan Produk yang Memiliki Pengerjaan Waktu Tercepat : ");
        List<Result> list = printJobScheduling(jobs);
        display(list);
        System.out.println("Total Profit " + maxprofit);
        System.out.println("Total Waktu Pengerjaan " + maxProcess_time);
    }
}