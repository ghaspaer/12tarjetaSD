package pgi.ghaspaer.tarjetaSD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TarjetaSDActivity extends Activity {
	EditText et1;
	EditText et2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
	}

	public void grabar(View v) {
		String nomarchivo = et1.getText().toString();
		String contenido = et2.getText().toString();
		try {
			File tarjeta = Environment.getExternalStorageDirectory();
			File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file));
			osw.write(contenido);
			osw.flush();
			osw.close();
			Toast.makeText(this, "Los datos fueron grabados correctamente",
					Toast.LENGTH_SHORT).show();
			et1.setText("");
			et2.setText("");
		} catch (IOException ioe) {
		}
	}

	public void recuperar1(View v) {
		String nomarchivo = et1.getText().toString();
		File tarjeta = Environment.getExternalStorageDirectory();
		File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
		try {
			FileInputStream fIn = new FileInputStream(file);
			InputStreamReader archivo = new InputStreamReader(fIn);
			BufferedReader br = new BufferedReader(archivo);
			String linea = br.readLine();
			String todo = "";
			while (linea != null) {
				todo = todo + linea + "\n";
				linea = br.readLine();
				System.out.println(""+todo);
			}
			br.close();
			archivo.close();
			Toast.makeText(this,todo,
					Toast.LENGTH_SHORT).show();
			et2.setText(todo);

		} catch (IOException e) {
		}
	}
}