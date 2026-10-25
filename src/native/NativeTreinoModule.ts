import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

type TreinoDTO = {
  //dto = data transfer object
  name: string;
  type: string;
  classes: string;
  id: number;
};

export interface Spec extends TurboModule {
  ping(): Promise<string>; //o retorno da funcao pint e uma promise(funcao) do tipo string
  addTreino(name: string, type: string, classes: string): Promise<number>; // dps dos : e tud oq sai, no caso sai um promise (um retorno dde funcao) q e um number, no caso e o nosso id.
  updateTreino(
    id: number,
    name: string,
    type: string,
    classes: string,
  ): Promise<void>; //void nao devolve nada pq altera os dados mas o id continua o mesmo
  delete(id: number): Promise<void>; //void significa que nao devolve nada
  listClass(classes: string): Promise<Array<TreinoDTO>>;
  listType(type: string): Promise<Array<TreinoDTO>>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('TreinoModule');
