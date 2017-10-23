/* Hello World program */

#include <stdio.h>

int main() {
	int i = 0;

	switch(i){
		case 1:
			printf("UNO\n");
		break;
		
		case 2:
			printf("DOS\n");
		break;

	case 3:
	  i = 5;
	  break;
		
		default:
			i++;
		break;
	}
	while(i>=0){
		printf("CODE2CHART\n");
		j = 3;
		i--;
	}

	do{
		printf("DO FUNCTION %d \n",i);
		if(true) i++;
		i++;
	} while(i<10);
}
