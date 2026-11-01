import { useState } from 'react';
import { Button, Text, View } from 'react-native';
import NativeTreinoModule from './src/native/NativeTreinoModule';

function App() {
  const [resultado, setResultado] = useState('');

  const testar = async () => {
    try {
      const resposta = await NativeTreinoModule.ping();
      setResultado(resposta);
    } catch (e) {
      setResultado('Erro: ' + String(e));
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', padding: 24 }}>
      <Button title="Testar ping" onPress={testar} />
      <Text style={{ marginTop: 16 }}>{resultado}</Text>
    </View>
  );
}

export default App;
