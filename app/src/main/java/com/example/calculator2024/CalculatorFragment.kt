package com.example.calculator2024

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculator2024.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "Binding problem"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCalculatorBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtons()
    }

    private fun initButtons() {
        with(binding) {

            button0.setOnClickListener {
                appendSymbol("0")
            }

            button1.setOnClickListener {
                appendSymbol("1")
            }

            button2.setOnClickListener {
                appendSymbol("2")
            }

            button3.setOnClickListener {
                appendSymbol("3")
            }

            button4.setOnClickListener {
                appendSymbol("4")
            }
            button5.setOnClickListener {
                appendSymbol("5")
            }

            button6.setOnClickListener {
                appendSymbol("6")
            }

            button7.setOnClickListener {
                appendSymbol("7")
            }

            button8.setOnClickListener {
                appendSymbol("8")
            }

            button9.setOnClickListener {
                appendSymbol("9")
            }

            buttonAC.setOnClickListener {
                inputPlace.text = "0"
            }

            buttonBraces.setOnClickListener {
                appendSymbol("(")
            }
        }
    }

    private fun appendSymbol(symbol: String) {
        if (binding.inputPlace.text[0] == '0') {
            binding.inputPlace.text = ""
        }
        binding.inputPlace.append(symbol)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}