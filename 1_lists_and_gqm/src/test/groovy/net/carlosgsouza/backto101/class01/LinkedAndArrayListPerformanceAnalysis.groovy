package net.carlosgsouza.backto101.class01

import spock.lang.Specification

public class LinkedAndArrayListPerformanceAnalysis extends Specification {
	
	def "Is it faster to add elements at the end of a linked list?"() {
		given: "A linked and an array list"
		List<Integer> linkedList
		List<Integer> arrayList
		
		and: "the test parameters"
		int ROUNDS = 10
		int MEASUREMENTS = 100
		int ITENS_PER_MEASUREMENT = 10000
		
		and: "arrays to store the measurements"
		int[] linkedListMeasurements = new int[MEASUREMENTS + 1]
		int[] arrayListMeasurements = new int[MEASUREMENTS + 1]
		
		when: "measuring the performance of a linked list"
		ROUNDS.times {
			long start = System.currentTimeMillis()
			
			linkedList = new LinkedList<Integer>()
			
			MEASUREMENTS.times { i -> 
				linkedListMeasurements[i] += System.currentTimeMillis() - start
				
				ITENS_PER_MEASUREMENT.times { 
					linkedList.add(i)	
				}
			}
			linkedListMeasurements[MEASUREMENTS] += System.currentTimeMillis() - start
		}
		
		and: "measuring the performance of an array list"
		ROUNDS.times {
			long start = System.currentTimeMillis()
			
			arrayList = new ArrayList<Integer>()
			MEASUREMENTS.times { i ->
				arrayListMeasurements[i] += System.currentTimeMillis() - start
				
				ITENS_PER_MEASUREMENT.times {
					arrayList.add(i)
				}
			}
			arrayListMeasurements[MEASUREMENTS] += System.currentTimeMillis() - start
		}
		
		then: "print the results"
		
		println "i\tLinked\tArray"
		(MEASUREMENTS + 1).times {
			def numberOfItems = it * ITENS_PER_MEASUREMENT
			def linkedListTime = linkedListMeasurements[it] / ROUNDS
			def arrayListTime = arrayListMeasurements[it] / ROUNDS
			
			println "$numberOfItems\t$linkedListTime\t$arrayListTime"
		}
		
		and: "answer the question"
		linkedListMeasurements[MEASUREMENTS] < arrayListMeasurements[MEASUREMENTS]
	}
	
	def "Is it faster to sort elements in ArrayList?"() {
		given: "A linked and an array list"
		List<Integer> linkedList
		List<Integer> arrayList
		Random random = new Random()
		
		and: "the test parameters"
		int ROUNDS = 10
		int MEASUREMENTS = 100
		int LIST_SIZE = 1000
		
		and: "arrays to store the measurements"
		int[] linkedListMeasurements = new int[MEASUREMENTS + 1]
		int[] arrayListMeasurements = new int[MEASUREMENTS + 1]
		
		when: "measuring the performance of a linked list"
		ROUNDS.times {round->
			long start = System.currentTimeMillis()
			
			linkedList = new LinkedList<Integer>()

			LIST_SIZE.times{i->
				linkedList.add(random.nextInt(100000+1))
			}
			//println "unsorted list: $linkedList"
			//println "ROUND\tMEASUREMENT\tLinkedListResult"
			MEASUREMENTS.times { i ->
				linkedListMeasurements[i] += System.currentTimeMillis() - start
				linkedList.sort()
				///println "$round\t$i\t$linkedListMeasurements[i]"
			}
			linkedListMeasurements[MEASUREMENTS] += System.currentTimeMillis() - start
			//println "Sorted list: $linkedList"
		}
		
		and: "measuring the performance of an array list"
		ROUNDS.times {
			long start = System.currentTimeMillis()
			
			arrayList = new ArrayList<Integer>()
			
			LIST_SIZE.times{i->
				arrayList.add(random.nextInt(100000+1))
			}
			//println "unsorted arraylist: $arrayList"
			MEASUREMENTS.times { i ->
				arrayListMeasurements[i] += System.currentTimeMillis() - start
				arrayList.sort()
			}
			arrayListMeasurements[MEASUREMENTS] += System.currentTimeMillis() - start
			//println "Sorted arraylist: $linkedList"
		}
		
		then: "print the results"
		
		println "Measurement\tLinked\tArray"
		(MEASUREMENTS + 1).times {
			def linkedListTime = linkedListMeasurements[it] / ROUNDS
			def arrayListTime = arrayListMeasurements[it] / ROUNDS
			
			println "$it\t$linkedListTime\t$arrayListTime"
		}
		
		and: "answer the question"
		linkedListMeasurements[MEASUREMENTS] > arrayListMeasurements[MEASUREMENTS]
	}
}
