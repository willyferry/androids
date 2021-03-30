package com.example.submission

object CoursesData {
    private val CourseTitles = arrayOf(
        "Memulai Pemrograman Dengan C",
        "Belajar Dasar Visualisasi Data",
        "Memulai Pemrograman Dengan Python",
        "Belajar Machine Learning untuk Pemula",
        "Belajar Pengembangan Machine Learning",
        "Memulai Pemrograman Dengan Java",
        "Memulai Pemrograman Dengan Kotlin",
        "Belajar Prinsip Pemrograman SOLID",
        "Belajar Membuat Aplikasi Android untuk Pemula",
        "Belajar Fundamental Aplikasi Android"
    )

    private val CourseDetails = arrayOf(
        "Bahasa C adalah bahasa prosedural yang memiliki banyak kegunaan. Ia didesain untuk di-compile secara sederhana agar mendukung akses ke low-level memory, sebagai pendukung bahasa yang dapat digunakan dalam instruksi mesin, dan untuk membutuhkan runtime support yang minimalis. Meskipun didesain secara minimal, C bisa digunakan sebagai bahasa yang multi-platform. Bahasa C sangatlah cocok bagi Anda yang ingin masuk ke dunia programming. Bahasa C terkenal dengan bahasanya yang simpel sehingga mudah untuk dipelajari. Oleh karena itu, bahasa C sering digunakan sebagai bahasa pertama untuk belajar pemrograman. Berdasarkan data dari Github, bahasa C adalah salah satu dari Top 10 Popular Language. \n Kelas ini didesain oleh Dr. Ir. Inggriani Liem (ITB/IA TOKI/BEBRAS NBO), dan disesuaikan untuk Anda yang ingin belajar pemrograman dari dasar. Bahasa C sangatlah cocok untuk digunakan sebagai batu loncatan untuk mempelajari bahasa-bahasa populer pemrograman seperti Java, Kotlin, C++/C#, dan lainnya.",
        "Setiap perusahaan pasti memiliki data yang jumlahnya tidak sedikit. Menurut World Economic Forum, 90% dari data di dunia dibuat dalam rentang waktu 2 tahun terakhir. Dari begitu banyaknya data pasti semakin sulit untuk mengelola dan memahaminya. Di sinilah pentingnya visualisasi data yang berperan untuk mengubah data yang kompleks menjadi lebih mudah dipahami banyak orang. Bahkan sebagai seorang developer pun, skill visualisasi data dibutuhkan supaya dapat menceritakan dan mempresentasikan data yang ada.",
        "Python adalah bahasa pemrograman interpretatif yang dapat digunakan di berbagai platform dengan filosofi perancangan yang berfokus pada tingkat keterbacaan kode dan merupakan salah satu bahasa populer yang berkaitan dengan Data Science, Machine Learning, dan Internet of Things (IoT). Keunggulan Python yang bersifat interpretatif juga banyak digunakan untuk prototyping, scripting dalam pengelolaan infrastruktur, hingga pembuatan website berskala besar. Menurut jurnal Developer Economics - State of the Developer Nation, pada akhir 2018 sebesar 69% dari pengembang machine learning dan data scientist menggunakan Python. Selain itu, menurut IEEE Spectrum pada tahun 2019 ini, Python kembali mempertahankan posisinya sebagai bahasa pemrograman paling populer di dunia.\n Kelas ini didesain oleh Dicoding bersama Google beserta para innovator dan engineer pada industri teknologi yang setiap harinya menggunakan Python sebagai bahasa pemrogramannya seperti Akhmat Safrudin (Python-ID), Doni Rubiagatra (Kumparan), Oon Arfiandwi (7Langit), Sofian Hadiwijaya (Warung Pintar), dan Tegar Imansyah (Python-ID Surabaya)." ,
        "Data sudah menjadi komoditi yang laku untuk diperjual-belikan. Sangat penting untuk mengetahui bagaimana data dapat diproses karena dengan pemrosesan data inilah suatu data yang banyak dapat dijadikan sebagai informasi yang bernilai tinggi. Salah satu pekerjaan yang berhubungan dengan pemrosesan data ini adalah Machine Learning Developer yang memiliki pengetahuan untuk menggunakan pembelajaran mesin untuk mengenali pattern yang tersembunyi di antara banyak data dan bagaimana menggunakannya. Sebagai contoh, 75% dari pengguna Netflix memilih film berdasarkan rekomendasi algoritma machine learning aplikasi tersebut. Menurut Forbes, pasar machine learning dunia akan mengalami pertumbuhan tahunan sebesar 44%. Pasar ini akan menjadi salah satu pasar paling menguntungkan di dunia IT.\n Kelas ini membahas mengenai dasar-dasar yang harus dipahami dalam pembelajaran mesin (machine learning) seperti data, model, kernel, neural networks, dan tensorflow. Kurikulum telah divalidasi IBM dan disusun oleh tim expert Dicoding bersama praktisi industri supaya materi yang disajikan terstruktur dan komprehensif.",
        "Posisi pengembang ML (machine learning) sangat dicari sehingga seorang ahli bisa mendapatkan pekerjaan senilai Rp 1,9 miliar per tahun (data Kompas). Hal ini karena otomatisasi telah mengubah cara orang hidup dan bekerja orang setiap harinya, sehingga pekerjaan dengan kecerdasan buatan terbukti menjadi peluang karir terbesar di zaman ini. Sebagai contoh, 75% dari pengguna Netflix memilih film berdasarkan rekomendasi algoritma machine learning aplikasi tersebut. Bidang ini juga sangat penting untuk dipahami, terlihat dari pernyataan Presiden Joko Widodo pada awal Desember 2019 yang menjabarkan rencana pemerintah untuk mengganti eselon III/IV dengan kecerdasan buatan. Menurut Forbes, kebutuhan seperti ini akan mendorong pasar ML dunia untuk mengalami pertumbuhan tahunan sebesar 44%.\n Kelas ini membahas implementasi machine learning menggunakan tensorflow yang sering ditemui di industri, seperti membuat model untuk masalah computer vision, pemrosesan bahasa alami, prediksi data time series, sistem rekomendasi, serta reinforcement learning. Kelas ini juga akan mengajarkan bagaimana cara deploy model ML Anda ke produksi dan tips-tips dalam melakukannya. Kurikulum telah disusun oleh tim expert Dicoding bersama Google agar materi yang disajikan terstruktur dan komprehensif.",
        "Java adalah sebuah bahasa yang diciptakan oleh James Gosling di tahun 1990-an. Java muncul sebagai bahasa yang dapat dijalankan di berbagai platform tanpa perlu melakukan re-kompilasi. Berdasarkan TIOBE Programming Community Index yang meninjau popularitas bahasa pemrograman, Java masih menjadi bahasa pemrograman nomor satu di dunia. Data dari Oracle menyatakan bahwa bahasa Java digunakan 90% perusahaan terkemuka yang masuk dalam daftar Fortune 500. Bahasa Java dapat digunakan untuk mengembangkan aplikasi pada platform desktop, web, mobile, hingga embedded dan IoT.\n Kelas ini didesain oleh para pelaku industri bersama tim expert dari Dicoding. Materi telah direview oleh Dr. Ir. Inggriani Liem (ITB/IA TOKI/BEBRAS NBO) dan sudah disesuaikan untuk Anda yang ingin mempelajari konsep Pemrograman Berorientasi Objek (PBO) maupun mempelajari struktur bahasa Java secara umum.",
        "Kotlin merupakan bahasa utama yang digunakan dalam pengembangan Android. Saat ini, Kotlin sudah stabil dan banyak digunakan oleh industri, seperti Gojek, Netflix, Twitter dan masih banyak lagi. Selain pengembangan Android, Kotlin dapat digunakan untuk berbagai macam pengembangan aplikasi, baik itu server atau backend, maupun website. Bahkan saat ini tengah dikembangkan Kotlin/Native, yang memungkinkan developer untuk menggunakannya sebagai bahasa pemrograman dalam pengembangan aplikasi di platform lain seperti embedded system, desktop, macOS, dan iOS. Banyaknya komunitas yang berkontribusi untuk Kotlin membuat bahasa ini berkembang sangat pesat. Kotlin dinobatkan sebagai “Fastest growing languages” oleh GitHub Octoverse 2018 mengalahkan Rust, Go, dll.\n Kelas ini disusun oleh expert developer kami berkolaborasi dengan Deny Prasetyo (Senior Software and Infrastructure Engineer, Gojek). Materi dalam kelas ini disesuaikan untuk Anda yang ingin memahami konsep-konsep dasar Kotlin, functional programming, Object-Oriented Programming (OOP), concurrency pada Kotlin, dan lain-lain.",
        "Semua orang bisa membuat kode program selama dia tahu suatu bahasa pemrograman. Tetapi membuat kode program yang mudah dimengerti, mudah dikelola, dan mudah dikembangkan adalah tantangan yang nyata, bahkan untuk seorang yang sudah menjadi programmer sekali pun. Tergantung dari paradigma apa yang dipakai, pasti ada solusi yang bisa digunakan untuk menyelesaikan problem tersebut. Di dalam paradigma OOP (object oriented programming atau pemrograman berorientasi object), dikenal prinsip SOLID yang memiliki tujuan untuk membuat kode program lebih mudah dimengerti, mudah dikelola, dan mudah dikembangkan. Robert C Martin (Uncle Bob) adalah seorang engineer dan instructor dari Amerika mengenalkan SOLID di papernya yang berjudul Design Principle and Design Pattern pada tahun 2000.\n Di dalam kelas ini akan dibahas mengenai lima prinsip utama dalam SOLID yaitu Single Responsibility, Open-Closed, Liskov Substitution, Interface Segregation, dan Dependency Inversion. Akan dibahas juga mengenai pilar utama dalam OOP dan hubungan antar objek. Kurikulum telah disusun dan diverifikasi oleh tim expert Dicoding bersama praktisi industri agar materi yang disajikan terstruktur dan komprehensif.",
        "Android semakin digandrungi. Per Maret 2018 ada lebih dari 3,6 juta aplikasi Android di Google Play Store (data Statista). Di indonesia sendiri pada bulan Maret 2019 sebanyak 93,5% konsumen memilih platform Android untuk sistem operasi peranti mobile mereka (data Statcounter). Ini menandakan bahwa kebutuhan akan Android developer, semakin meningkat. Tak heran, profesi Android developer merupakan 1 dari 5 profesi yang paling diincar perusahaan (data LinkedIn Emerging Jobs Report 2019). \n Dicoding sebagai satu-satunya Google Developers Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini.",
        "Android semakin digandrungi. Per Maret 2020 ada lebih dari 2,8 juta aplikasi Android di Google Play Store (data Statista). Di indonesia sendiri pada bulan Maret 2019 sebanyak 93,5% konsumen memilih platform Android untuk sistem operasi peranti mobile mereka (data Statcounter). Ini menandakan bahwa kebutuhan akan Android developer, semakin meningkat. Tak heran, profesi Android developer merupakan 1 dari 5 profesi yang paling diincar perusahaan (data LinkedIn Emerging Jobs Report 2019). \n Dicoding sebagai satu-satunya Google Developers Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini.",
    )

    private val CourseThumbnails = intArrayOf(
        R.drawable.memulai_pemrograman_dengan_c,
        R.drawable.belajar_dasar_visualisasi_data,
        R.drawable.memulai_pemrograman_dengan_python,
        R.drawable.belajar_machine_learning_untuk_pemula,
        R.drawable.belajar_pengembangan_machine_learning,
        R.drawable.memulai_pemrograman_dengan_java,
        R.drawable.memulai_pemrograman_dengan_kotlin,
        R.drawable.belajar_prinsip_pemrograman_solid,
        R.drawable.belajar_membuat_aplikasi_android_untuk_pemula,
        R.drawable.menjadi_android_developer_expert
    )

    private val CourseCategory = arrayOf(
            "",
            "Machine Learning Developer",
            "Machine Learning Developer",
            "Machine Learning Developer",
            "Machine Learning Developer",
            "",
            "Android Developer",
            "Multi-Platform App Developer",
            "Android Developer",
            "Android Developer",
    )

    private val CourseModul = arrayOf(
            "42",
            "50",
            "39",
            "52",
            "63",
            "40",
            "114",
            "28",
            "28",
            "145",
    )

    private val CourseTime = arrayOf(
            "15",
            "16",
            "20",
            "30",
            "40",
            "40",
            "50",
            "15",
            "40",
            "150",
    )

    private val CourseLevel = arrayOf(
            "Dasar",
            "Dasar",
            "Dasar",
            "Pemula",
            "Menengah",
            "Dasar",
            "Dasar",
            "Dasar",
            "Pemula",
            "Menengah",
    )

    private val CourseRate = arrayOf(
            "",
            "4.86",
            "4.75",
            "4.76",
            "4.82",
            "",
            "4.88",
            "4.88",
            "4.89",
            "4.94",
    )

    val listData: ArrayList<Course>
        get() {
            val list = arrayListOf<Course>()
            for(position in CourseTitles.indices) {
                val course = Course(
                        title = CourseTitles[position],
                        category = CourseCategory[position],
                        detail = CourseDetails[position],
                        thumbnail = CourseThumbnails[position],
                        modul = CourseModul[position],
                        time = CourseTime[position],
                        level = CourseLevel[position],
                        rate = CourseRate[position],
                )
                list.add(course)
            }
            return list
        }
}